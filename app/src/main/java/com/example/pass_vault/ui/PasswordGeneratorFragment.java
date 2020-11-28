package com.example.pass_vault.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.pass_vault.R;
import com.example.pass_vault.utilities.CopyUtility;
import com.example.pass_vault.utilities.PasswordGenerator;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class PasswordGeneratorFragment extends Fragment {

    private static final String TAG = "PasswordGeneratorFrag";
    EditText editTextGeneratedPassword;
    ImageView imgCopy;
    Button btnGeneratePassword;
    Slider nCharactersSlider;
    SwitchCompat switchUppercase, switchLowercase, switchNumbers, switchSpecialCharacters;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_generator, container, false);

        if (this.getActivity() != null) {
            ((AppCompatActivity) this.getActivity()).getSupportActionBar().setTitle("Password Generator");
        }

        editTextGeneratedPassword = (EditText) view.findViewById(R.id.edit_generated_password);
        imgCopy = (ImageView) view.findViewById(R.id.img_copy_pass);
        btnGeneratePassword = (Button) view.findViewById(R.id.button_generate_password);
        nCharactersSlider = (Slider) view.findViewById(R.id.char_limit_slider);
        switchUppercase = (SwitchCompat) view.findViewById(R.id.switch_uppercase);
        switchLowercase = (SwitchCompat) view.findViewById(R.id.switch_lowercase);
        switchNumbers = (SwitchCompat) view.findViewById(R.id.switch_numbers);
        switchSpecialCharacters = (SwitchCompat) view.findViewById(R.id.switch_special_char);

        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyUtility.copy(editTextGeneratedPassword.getText().toString(), view.getContext());
                Snackbar.make(view, "Generated password copied!", Snackbar.LENGTH_SHORT).show();
            }
        });
        
        btnGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String generatedPassword = PasswordGenerator.generate(Math.round(nCharactersSlider.getValue()),
                        switchUppercase.isChecked(), switchLowercase.isChecked(),
                        switchNumbers.isChecked(), switchSpecialCharacters.isChecked());

                editTextGeneratedPassword.setText(generatedPassword);
            }
        });

        return view;
    }
}

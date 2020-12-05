package com.example.pass_vault.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pass_vault.R;
import com.example.pass_vault.utilities.KeyboardUtility;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CreateLoginFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText passcode, confirmPasscode;
    Button btnCreatePasscode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_login, container, false);

        sharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.preference_file_login), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        passcode = (EditText) view.findViewById(R.id.editTextLoginPass);
        confirmPasscode = (EditText) view.findViewById(R.id.editTextConfirmLoginPass);

        btnCreatePasscode = (Button) view.findViewById(R.id.buttonCreatePasscode);
        btnCreatePasscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validPasscode(passcode.getText().toString(), confirmPasscode.getText().toString())) {
                    savePasscode();
                    clearFields();
                    Objects.requireNonNull(getActivity()).recreate();
                    Snackbar.make(view, "Successfully created passcode!", Snackbar.LENGTH_SHORT).show();
                } else {
                    KeyboardUtility.hideKeyboard(Objects.requireNonNull(getActivity()));
                    Snackbar.make(view, "Passcodes must not be empty, must match, and must be less than 5 digits.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean validPasscode(String passcode, String passcode2) {
        return passcode.equals(passcode2) && passcode.length() <= 5 && passcode2.length() <= 5
                && !passcode.isEmpty();
    };

    private void savePasscode() {
        editor.putString(getString(R.string.saved_passcode), passcode.getText().toString());
        editor.apply();
    }

    private void clearFields() {
        passcode.setText("");
        confirmPasscode.setText("");
    }
}
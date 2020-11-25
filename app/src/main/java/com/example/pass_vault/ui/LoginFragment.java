package com.example.pass_vault.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pass_vault.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginFragment extends Fragment {

    SharedPreferences sharedPreferences;
    ArrayList<Button> buttons;
    ArrayList<EditText> editTextList;
    ImageView imgBackSpace;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        sharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.preference_file_login), Context.MODE_PRIVATE);

        initButtons();
        initEditTexts();

        imgBackSpace = (ImageView) view.findViewById(R.id.image_back_space);
        imgBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSpace();
            }
        });

        return view;
    }

    private class LoginButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < buttons.size(); i++) {
                if (view.getId() == buttons.get(i).getId()) {
                    enterNumber(Integer.toString(i));
                }
            }

            if (!editTextList.get(editTextList.size() - 1).getText().toString().isEmpty()) {
                checkPasscode();
            }
        }
    }

    private void initButtons() {
        buttons = new ArrayList<>();
        buttons.add((Button) view.findViewById(R.id.button_0));
        buttons.add((Button) view.findViewById(R.id.button_1));
        buttons.add((Button) view.findViewById(R.id.button_2));
        buttons.add((Button) view.findViewById(R.id.button_3));
        buttons.add((Button) view.findViewById(R.id.button_4));
        buttons.add((Button) view.findViewById(R.id.button_5));
        buttons.add((Button) view.findViewById(R.id.button_6));
        buttons.add((Button) view.findViewById(R.id.button_7));
        buttons.add((Button) view.findViewById(R.id.button_8));
        buttons.add((Button) view.findViewById(R.id.button_9));

        for (Button button : buttons) {
            button.setOnClickListener(new LoginFragment.LoginButtonListener());
        }
    }

    private void initEditTexts() {
        editTextList = new ArrayList<>();
        editTextList.add((EditText) view.findViewById(R.id.editTextPass0));
        editTextList.add((EditText) view.findViewById(R.id.editTextPass1));
        editTextList.add((EditText) view.findViewById(R.id.editTextPass2));
        editTextList.add((EditText) view.findViewById(R.id.editTextPass3));
        editTextList.add((EditText) view.findViewById(R.id.editTextPass4));
    }

    private void enterNumber(String number) {
        for (EditText edit : editTextList) {
            if (edit.getText().toString().length() <= 0) {
                edit.setText(number);
                break;
            }
        }
    }

    private void checkPasscode() {
        String savedPasscode = sharedPreferences.getString(getString(R.string.saved_passcode), "");
        String enteredPasscode = getEnteredPass();

        if (savedPasscode.equals(enteredPasscode)) {
            launchMainActivity();
        } else {
            clearFields();
            Snackbar.make(view, "Invalid passcode!", Snackbar.LENGTH_SHORT).show();
        }
    }

    private String getEnteredPass() {
        StringBuilder stringBuilder = new StringBuilder();

        for (EditText edit : editTextList) {
            stringBuilder.append(edit.getText().toString());
        }

        return stringBuilder.toString();
    }
    private void launchMainActivity() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

    private void clearFields() {
      for (EditText edit : editTextList) {
          edit.setText("");
      }
    }

    private void backSpace() {
        for (int i = editTextList.size() - 1; i >= 0; i--) {
            if (editTextList.get(i).getText().toString().length() > 0) {
                editTextList.get(i).setText("");
                break;
            }
        }
    }
}
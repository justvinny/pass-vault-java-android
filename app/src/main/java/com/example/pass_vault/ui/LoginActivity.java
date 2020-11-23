package com.example.pass_vault.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pass_vault.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

     ArrayList<Button> buttons;
     ArrayList<EditText> editTextList;
     ImageView imgBackSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initButtons();
        initEditTexts();

        imgBackSpace = (ImageView) findViewById(R.id.image_back_space);
        imgBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSpace();
            }
        });
    }

    private class LoginButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < buttons.size(); i++) {
                if (view.getId() == buttons.get(i).getId()) {
                    enterNumber(Integer.toString(i));
                }
            }

            checkPasswordFilled();
        }
    }

    private void initButtons() {
        buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.button_0));
        buttons.add((Button) findViewById(R.id.button_1));
        buttons.add((Button) findViewById(R.id.button_2));
        buttons.add((Button) findViewById(R.id.button_3));
        buttons.add((Button) findViewById(R.id.button_4));
        buttons.add((Button) findViewById(R.id.button_5));
        buttons.add((Button) findViewById(R.id.button_6));
        buttons.add((Button) findViewById(R.id.button_7));
        buttons.add((Button) findViewById(R.id.button_8));
        buttons.add((Button) findViewById(R.id.button_9));

        for (Button button : buttons) {
            button.setOnClickListener(new LoginButtonListener());
        }
    }

    private void initEditTexts() {
        editTextList = new ArrayList<>();
        editTextList.add((EditText) findViewById(R.id.editTextPass0));
        editTextList.add((EditText) findViewById(R.id.editTextPass1));
        editTextList.add((EditText) findViewById(R.id.editTextPass2));
        editTextList.add((EditText) findViewById(R.id.editTextPass3));
        editTextList.add((EditText) findViewById(R.id.editTextPass4));
    }

    private void enterNumber(String number) {
        for (EditText edit : editTextList) {
            if (edit.getText().toString().length() <= 0) {
                edit.setText(number);
                break;
            }
        }
    }

    private void checkPasswordFilled() {
        if (editTextList.get(editTextList.size() - 1).getText().toString().length() > 0) {
            launchMainActivity();
        }
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
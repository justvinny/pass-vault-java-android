package com.example.pass_vault.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.pass_vault.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_login), Context.MODE_PRIVATE);

        if (sharedPreferences.getString(getString(R.string.saved_passcode), "").isEmpty()) {
            setTitle("Create Passcode");

            getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_container,
                    new CreateLoginFragment()).commit();
        } else {
            setTitle("Login");

            getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment_container,
                    new LoginFragment()).commit();
        }
    }

    private void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
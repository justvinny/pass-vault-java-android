package com.example.pass_vault;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pass_vault.model.AccountItem;
import com.example.pass_vault.model.AccountsList;
import com.example.pass_vault.utilities.CSVUtility;
import com.example.pass_vault.utilities.KeyboardUtility;
import com.google.android.material.snackbar.Snackbar;

public class AddFragment extends Fragment {

    private AccountsList accounts;
    private EditText ePlatform, eUsername, ePassword, eReEnterPassword;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        accounts = new AccountsList();
        ePlatform = (EditText) view.findViewById(R.id.edit_platform);
        eUsername = (EditText) view.findViewById(R.id.edit_username);
        ePassword = (EditText) view.findViewById(R.id.edit_password);
        eReEnterPassword = (EditText) view.findViewById(R.id.edit_reenter_password);
        btnAdd = (Button) view.findViewById(R.id.button_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAccount(v);
            }
        });

        return view;
    }

    private void addAccount(View v) {
        KeyboardUtility.hideKeyboard(this.getActivity());

        String platform = ePlatform.getText().toString();
        String username = eUsername.getText().toString();
        String password = ePassword.getText().toString();
        String confirmPassword = eReEnterPassword.getText().toString();

        if (password.equals(confirmPassword)) {
            // Catch exception for blank input.
            try {
                AccountItem account = new AccountItem(platform, username, password);
                accounts.add(account);
                Snackbar.make(v, "Successfully added account!", Snackbar.LENGTH_SHORT)
                        .show();
                clearFields();
            } catch (IllegalArgumentException e) {
                Snackbar.make(v, e.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Snackbar.make(v, "Passwords do not match!", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        ePlatform.setText("");
        eUsername.setText("");
        ePassword.setText("");
        eReEnterPassword.setText("");
    }
}

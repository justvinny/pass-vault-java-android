package com.example.pass_vault.model;

import android.util.Log;

import com.example.pass_vault.utilities.CSVUtility;

import java.io.IOException;
import java.util.ArrayList;

public class AccountsList {
    private static final String TAG = "AccountsList";
    private ArrayList<AccountItem> accounts;

    public AccountsList() {
        load();
    }

    public void add(AccountItem account) {
        if (accounts.contains(account)) {
            String message = String.format("%s username for %s already exists.",
                    account.getUsername(), account.getPlatform());
            throw new IllegalArgumentException(message);
        }

        new Thread(() -> {
            accounts.add(account);
            save();
        }).start();
    }

    public void remove(AccountItem account) {
        accounts.remove(account);
    }

    public void remove (int index) {
        accounts.remove(index);
    }

    private void load() {
        try {
            accounts = CSVUtility.read();
        } catch (IOException e) {
            accounts = new ArrayList<AccountItem>();
        }
    }

    private void save() {
        try {
            CSVUtility.write(accounts);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }
}

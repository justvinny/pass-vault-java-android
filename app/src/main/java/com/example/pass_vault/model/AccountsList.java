package com.example.pass_vault.model;

import android.content.Context;
import android.util.Log;

import com.example.pass_vault.utilities.CSVUtility;

import java.io.IOException;
import java.util.ArrayList;

public class AccountsList {
    private static final String TAG = "AccountsList";
    private ArrayList<AccountItem> accounts;
    private Context context;

    public AccountsList(Context context) {
        this.context = context;
        load(context);
    }

    public void add(AccountItem account) {
        if (accounts.contains(account)) {
            String message = String.format("%s username for %s already exists.",
                    account.getUsername(), account.getPlatform());
            throw new IllegalArgumentException(message);
        }

        save(account, context);
    }

    public AccountItem get(int index) {
        return accounts.get(index);
    }

    public void remove(AccountItem account) {
        accounts.remove(account);
    }

    public void remove (int index) {
        accounts.remove(index);
    }

    public int size() {
        return accounts.size();
    }

    private void load(Context context) {
        accounts = CSVUtility.read(context);
    }

    private void save(AccountItem account, Context context) {
        new Thread(() -> {
            accounts.add(account);
            CSVUtility.write(accounts, context);
        }).start();
    }
}

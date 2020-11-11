package com.example.pass_vault.model;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;

import com.example.pass_vault.utilities.CSVUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountsList {
    private static final String TAG = "AccountsList";
    private LinkedBlockingQueue<AccountItem> accounts;
    private Context context;
    private AtomicBoolean isLoaded = new AtomicBoolean(false);

    public AccountsList(Context context) {
        this.context = context;
        accounts = new LinkedBlockingQueue<>();
        load();
    }

    public boolean getIsLoaded() { return isLoaded.get(); }
    public void setIsLoaded(boolean isLoaded) { this.isLoaded.set(isLoaded); }

    public void add(AccountItem account) {
        if (accounts.contains(account)) {
            String message = String.format("%s username for %s already exists.",
                    account.getUsername(), account.getPlatform());
            throw new IllegalArgumentException(message);
        }

        accounts.offer(account);
        save();
    }

    public AccountItem get(int index) {
        ArrayList<AccountItem> accountsArr = new ArrayList<>(accounts);

        return Objects.requireNonNull(accountsArr.get(index));
    }

    public void remove(AccountItem account) {
        accounts.remove(account);
    }

    public void remove (int index) {
        accounts.remove(get(index));
    }

    public int size() {
        return accounts.size();
    }

    public void load() {
        accounts.clear();
        CSVUtility.read(context, accounts);
    }

    public void save() {
        CSVUtility.write(context, accounts);
    }
}

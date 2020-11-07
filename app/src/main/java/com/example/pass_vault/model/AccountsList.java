package com.example.pass_vault.model;

import android.content.Context;
import android.util.Log;

import com.example.pass_vault.utilities.CSVUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountsList {
    private static final String TAG = "AccountsList";
    private Lock writeLock, readLock;
    private ArrayList<AccountItem> accounts;
    private Context context;

    {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        writeLock = rwLock.writeLock();
        readLock = rwLock.readLock();
    }

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

        try {
            writeLock.lock();
            save(account, context);
        } finally {
            writeLock.unlock();
        }
    }

    public AccountItem get(int index) {
        AccountItem account;

        try {
            readLock.lock();
            account = accounts.get(index);
        } finally {
            readLock.unlock();
        }

        return Objects.requireNonNull(account);
    }

    public void remove(AccountItem account) {
        try {
            writeLock.lock();
            accounts.remove(account);
            CSVUtility.write(accounts, context);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove (int index) {
        try {
            writeLock.lock();
            accounts.remove(index);
            CSVUtility.write(accounts, context);
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        return accounts.size();
    }

    private void load(Context context) {
        try {
            readLock.lock();
            accounts = CSVUtility.read(context);
        } finally {
            readLock.unlock();
        }
    }

    private void save(AccountItem account, Context context) {
        new Thread(() -> {
            accounts.add(account);
            CSVUtility.write(accounts, context);
        }).start();
    }
}

package com.example.pass_vault.data;

import android.content.Context;

import com.example.pass_vault.utilities.CSVUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountsList {
    private static final String TAG = "AccountsList";
    private LinkedBlockingDeque<AccountItem> accounts;
    private Context context;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private AtomicBoolean isLoaded = new AtomicBoolean(false);

    public AccountsList(Context context) {
        this.context = context;
        accounts = new LinkedBlockingDeque<>();
    }

    public boolean getIsLoaded() { return isLoaded.get(); }
    public void setIsLoaded(boolean isLoaded) { this.isLoaded.set(isLoaded); }

    public LinkedBlockingDeque<AccountItem> getAccounts() { return accounts; }
    public void add(AccountItem account) {
        if (accounts.contains(account)) {
            String message = String.format("%s username for %s already exists.",
                    account.getUsername(), account.getPlatform());
            throw new IllegalArgumentException(message);
        }

        accounts.push(account);
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
        try {
            readWriteLock.readLock().lock();
            accounts.clear();
            CSVUtility.read(context, accounts);
            setIsLoaded(true);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public void save() {
        try {
            readWriteLock.writeLock().lock();
            CSVUtility.write(context, accounts);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void sortPlatformAscending() {
        ArrayList<AccountItem> accountsArr = new ArrayList<>(accounts);

        Collections.sort(accountsArr, new Comparator<AccountItem>() {
            @Override
            public int compare(AccountItem t, AccountItem t1) {
                return t.getPlatform().compareTo(t1.getPlatform());
            }
        });

        accounts = new LinkedBlockingDeque<>(accountsArr);
    }

    public void sortPlatformDescending() {
        ArrayList<AccountItem> accountsArr = new ArrayList<>(accounts);

        Collections.sort(accountsArr, new Comparator<AccountItem>() {
            @Override
            public int compare(AccountItem t, AccountItem t1) {
                return t1.getPlatform().compareTo(t.getPlatform());
            }
        });

        accounts = new LinkedBlockingDeque<>(accountsArr);
    }
    public void sortUsernameAscending() {
        ArrayList<AccountItem> accountsArr = new ArrayList<>(accounts);

        Collections.sort(accountsArr, new Comparator<AccountItem>() {
            @Override
            public int compare(AccountItem t, AccountItem t1) {
                return t.getUsername().compareTo(t1.getUsername());
            }
        });

        accounts = new LinkedBlockingDeque<>(accountsArr);
    }

    public void sortUsernameDescending() {
        ArrayList<AccountItem> accountsArr = new ArrayList<>(accounts);

        Collections.sort(accountsArr, new Comparator<AccountItem>() {
            @Override
            public int compare(AccountItem t, AccountItem t1) {
                return t1.getUsername().compareTo(t.getUsername());
            }
        });

        accounts = new LinkedBlockingDeque<>(accountsArr);
    }
}

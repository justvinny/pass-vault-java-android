package com.example.pass_vault.data;

import android.accounts.Account;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountItemRepository {

    private AccountItemDao accountItemDao;
    private LiveData<List<AccountItem>> accountItems;

    public AccountItemRepository(Application application) {
        AccountsDatabase database = AccountsDatabase.getInstance(application);
        accountItemDao = database.accountItemDao();
        accountItems = accountItemDao.getAllAccountItems();
    }

    public void insert(AccountItem accountItem) {
        AccountsDatabase.dbWriterExecutor.execute(() -> {
            accountItemDao.insert(accountItem);
        });
    }

    public void update(AccountItem accountItem) {
        AccountsDatabase.dbWriterExecutor.execute(() -> {
            accountItemDao.update(accountItem);
        });
    }

    public void delete(AccountItem accountItem) {
        AccountsDatabase.dbWriterExecutor.execute(() -> {
            accountItemDao.delete(accountItem);
        });
    }

    public void delete(List<AccountItem> accountItemList) {
        AccountsDatabase.dbWriterExecutor.execute(() -> {
            accountItemDao.delete(accountItemList);
        });
    }

    public void deleteAll() {
        AccountsDatabase.dbWriterExecutor.execute(() -> {
            accountItemDao.deleteAll();
        });
    }

    public LiveData<List<AccountItem>> getAllAccountItems() {
        return accountItems;
    }
}

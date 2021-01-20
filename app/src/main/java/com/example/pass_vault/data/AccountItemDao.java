package com.example.pass_vault.data;

import android.accounts.Account;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountItemDao {

    @Insert
    public void insert(AccountItem accountItem);

    @Update
    public void update(AccountItem accountItem);

    @Delete
    public void delete(AccountItem accountItem);

    @Delete
    public void delete(List<AccountItem> accountItemList);

    @Query("SELECT * FROM account_item_table")
    public LiveData<List<AccountItem>> getAllAccountItems();

    @Query("DELETE FROM account_item_table")
    public void deleteAll();
}

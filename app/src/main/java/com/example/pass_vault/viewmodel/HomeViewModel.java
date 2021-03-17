package com.example.pass_vault.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pass_vault.data.AccountItem;
import com.example.pass_vault.data.AccountItemRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private AccountItemRepository accountItemRepository;
    private LiveData<List<AccountItem>> accountItemList;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        accountItemRepository = new AccountItemRepository(application);
        accountItemList = accountItemRepository.getAllAccountItems();
    }
}

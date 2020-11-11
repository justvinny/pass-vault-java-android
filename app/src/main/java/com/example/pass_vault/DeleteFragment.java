package com.example.pass_vault;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pass_vault.model.AccountItem;
import com.example.pass_vault.model.AccountsList;
import com.example.pass_vault.model.adapters.DeleteAccountsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DeleteFragment extends Fragment {

    private static final String TAG = "DeleteFragment";
    private AccountsList accounts;
    private ArrayList<AccountItem> accountsToRemove;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ProgressBar progressBar;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_fragment, container, false);

        accounts = new AccountsList(this.getContext());
        accountsToRemove = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.delete_recycler);
        recyclerView.setAdapter(new DeleteAccountsAdapter(accounts, accountsToRemove));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        fab = (FloatingActionButton) view.findViewById(R.id.fab_delete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    loadingAnimation();
                    deleteSelected();
                    loadingAnimation();

                    handler.post(() -> {
                        updateAdapter();
                        Snackbar.make(recyclerView, "Selected Items have been deleted!", Snackbar.LENGTH_SHORT).show();
                    });

                    accounts.setIsLoaded(false);
                }).start();
            }
        });

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar_delete);

        new Thread(() -> {
            accounts.load();
            loadingAnimation();
            accounts.setIsLoaded(false);
        }).start();

        return view;
    }

    private void deleteSelected() {
        for (AccountItem item : accountsToRemove) {
            accounts.remove(item);
        }

        accountsToRemove.clear();
        accounts.save();
        accounts.load();
    }

    private void updateAdapter() {
        recyclerView.setAdapter(new DeleteAccountsAdapter(accounts, accountsToRemove));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void loadingAnimation() {
        if (accounts.getIsLoaded()) {
            handler.post(() -> {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        } else {
            handler.post(() -> {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            });
        }
    }
}

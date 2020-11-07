package com.example.pass_vault;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private AccountsList accounts;
    private ArrayList<AccountItem> accountsToRemove;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

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
                deleteSelected();

                Snackbar.make(recyclerView, "Selected Items have been deleted!", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void deleteSelected() {
        for (AccountItem item : accountsToRemove) {
            accounts.remove(item);
        }

        recyclerView.getAdapter().notifyDataSetChanged();
    }
}

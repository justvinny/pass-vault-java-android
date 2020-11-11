package com.example.pass_vault;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pass_vault.model.AccountsList;
import com.example.pass_vault.model.adapters.HomeAccountsAdapter;

public class HomeFragment extends Fragment {

    private AccountsList accounts;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        accounts = new AccountsList(this.getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        recyclerView.setAdapter(new HomeAccountsAdapter(accounts));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}

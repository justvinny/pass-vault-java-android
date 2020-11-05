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

import com.example.pass_vault.model.adapters.AccountsAdapter;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        recyclerView.setAdapter(new AccountsAdapter(this.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}

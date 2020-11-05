package com.example.pass_vault.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pass_vault.R;
import com.example.pass_vault.model.AccountItem;
import com.example.pass_vault.utilities.CopyUtility;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder> {

    private ArrayList<AccountItem> accounts = new ArrayList<>();

    public AccountsAdapter() {
        mockData();
    }

    @NonNull
    @Override
    public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.account_item, parent, false);

        AccountsViewHolder viewHolder = new AccountsViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
        holder.textNumber.setText(String.format(Locale.getDefault(), "%d", position + 1));
        holder.textPlatform.setText((accounts.get(position).getPlatform()));
        holder.textUsername.setText(accounts.get(position).getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyUtility.copy(accounts.get(position).getPassword(), holder.itemView.getContext());

                String snackMessage = "Copied password for " + accounts.get(position).getUsername()
                        + " to clipboard!";
                Snackbar.make(holder.itemView, snackMessage, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void mockData() {
        accounts.add(new AccountItem("Netflix", "User_1", "123456789"));
        accounts.add(new AccountItem("Amazon", "User_2", "123456789test1"));
        accounts.add(new AccountItem("Hulu", "User_3", "123456789test2"));
        accounts.add(new AccountItem("Facebook", "User_4", "123456789test3"));
        accounts.add(new AccountItem("TradeMe", "User_5", "123456789test4"));
        accounts.add(new AccountItem("ASB", "User_6", "123456789test5"));
        accounts.add(new AccountItem("Paypal", "User_7", "123456789test6"));
        accounts.add(new AccountItem("Patreon", "User_8", "123456789test7"));
        accounts.add(new AccountItem("OnlyFans", "User_9", "123456789test8"));
        accounts.add(new AccountItem("Twitter", "User_10", "123456789test9"));
        accounts.add(new AccountItem("Instagram", "User_11", "123456789test10"));
        accounts.add(new AccountItem("Friendster", "User_12", "123456789test11"));
        accounts.add(new AccountItem("Spotify", "User_13", "123456789test12"));
        accounts.add(new AccountItem("Firebase", "User_14", "123456789test13"));
        accounts.add(new AccountItem("Google", "User_15", "123456789test14"));
        accounts.add(new AccountItem("Yahoo", "User_16", "123456789test15"));
        accounts.add(new AccountItem("Hotmail", "User_17", "123456789test16"));
        accounts.add(new AccountItem("YouTube", "User_18", "123456789test17"));
        accounts.add(new AccountItem("XDA", "User_19", "123456789test18"));
    }

    public class AccountsViewHolder extends RecyclerView.ViewHolder {

        private TextView textNumber, textPlatform, textUsername;

        public AccountsViewHolder(@NonNull View itemView) {
            super(itemView);

            textNumber = (TextView) itemView.findViewById(R.id.text_number);
            textPlatform = (TextView) itemView.findViewById(R.id.text_platform);
            textUsername = (TextView) itemView.findViewById(R.id.text_username);
        }
    }
}

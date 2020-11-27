package com.example.pass_vault.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pass_vault.R;
import com.example.pass_vault.data.AccountsList;
import com.example.pass_vault.utilities.CopyUtility;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class HomeAccountsAdapter extends RecyclerView.Adapter<HomeAccountsAdapter.AccountsViewHolder> {

    private AccountsList accounts;

    public HomeAccountsAdapter(AccountsList accounts) {
        this.accounts = accounts;
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

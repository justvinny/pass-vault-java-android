package com.example.pass_vault.model.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.audiofx.AcousticEchoCanceler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pass_vault.R;
import com.example.pass_vault.model.AccountItem;
import com.example.pass_vault.model.AccountsList;
import com.example.pass_vault.utilities.CopyUtility;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class DeleteAccountsAdapter extends RecyclerView.Adapter<DeleteAccountsAdapter.DeleteViewHolder> {

    AccountsList accounts;
    ArrayList<AccountItem> accountsToRemove;

    public DeleteAccountsAdapter(AccountsList accounts, ArrayList<AccountItem> accountsToRemove) {
        this.accounts = accounts;
        this.accountsToRemove = accountsToRemove;
    }

    @NonNull
    @Override
    public DeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.account_item, parent, false);

        DeleteViewHolder viewHolder = new DeleteViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteViewHolder holder, int position) {
        holder.textNumber.setText(String.format(Locale.getDefault(), "%d", position + 1));
        holder.textPlatform.setText((accounts.get(position).getPlatform()));
        holder.textUsername.setText(accounts.get(position).getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = ((ColorDrawable) holder.layout.getBackground()).getColor();
                int white = holder.itemView.getResources().getColor(R.color.white);
                int indigo = holder.itemView.getResources().getColor(R.color.indigo_400);

                if (current == white) {
                    holder.layout.setBackgroundColor(indigo);
                    accountsToRemove.add(accounts.get(position));
                } else {
                    holder.layout.setBackgroundColor(white);
                    accountsToRemove.remove(accounts.get(position));
                }
            }
        });
    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        TextView textNumber, textPlatform, textUsername;

        public DeleteViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = (ConstraintLayout) itemView.findViewById(R.id.item_layout);
            textNumber = (TextView) itemView.findViewById(R.id.text_number);
            textPlatform = (TextView) itemView.findViewById(R.id.text_platform);
            textUsername = (TextView) itemView.findViewById(R.id.text_username);
        }
    }
}

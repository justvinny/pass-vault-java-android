package com.example.pass_vault.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pass_vault.R;
import com.example.pass_vault.data.AccountItem;
import com.example.pass_vault.data.AccountsList;
import com.example.pass_vault.data.adapters.HomeAccountsAdapter;
import com.example.pass_vault.utilities.MenuItemTextColorUtility;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private AccountsList accounts;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        accounts = new AccountsList(this.getContext());

        Toolbar toolbar = view.findViewById(R.id.home_toolbar);

        if (((AppCompatActivity) this.getActivity()) != null) {
            Objects.requireNonNull(((AppCompatActivity) this.getActivity()).getSupportActionBar()).hide();
            ((AppCompatActivity) this.getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) this.getActivity()).getSupportActionBar().setTitle("Home");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
        recyclerView.setAdapter(new HomeAccountsAdapter(accounts));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar_home);

        new Thread(() -> {
            accounts.load();

           if (accounts.getIsLoaded()) {
               handler.post(() -> {
                   if (recyclerView.getAdapter() != null) {
                       recyclerView.getAdapter().notifyDataSetChanged();
                       progressBar.setVisibility(View.GONE);
                       recyclerView.setVisibility(View.VISIBLE);
                   }
               });

               accounts.setIsLoaded(false);
           }
        }).start();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this.getActivity() != null) {
            Toolbar toolbar = this.getActivity().findViewById(R.id.toolbar);
            ((AppCompatActivity) this.getActivity()).setSupportActionBar(toolbar);
            Objects.requireNonNull(((AppCompatActivity) this.getActivity()).getSupportActionBar()).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.app_bar_menu, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Search username");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                new Thread(() -> {
                    AccountsList tempAccounts = new AccountsList(getContext());

                    for (AccountItem account : accounts.getAccounts()) {
                        if (account.getUsername().contains(newText)) {
                            tempAccounts.add(account);
                        }
                    }

                    updateAdapter(tempAccounts);
                }).start();

                return true;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
                }
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (getActivity() != null) {
                    Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(true);
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_platform_asc:
                accounts.sortPlatformAscending();
                updateAdapter(accounts);
                return true;
            case R.id.sort_platform_desc:
                accounts.sortPlatformDescending();
                updateAdapter(accounts);
                return true;
            case R.id.sort_username_asc:
                accounts.sortUsernameAscending();
                updateAdapter(accounts);
                return true;
            case R.id.sort_username_desc:
                accounts.sortUsernameDescending();
                updateAdapter(accounts);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateAdapter(AccountsList accounts) {
        if (recyclerView.getAdapter() != null) {
            handler.post(() -> {
                recyclerView.setAdapter(new HomeAccountsAdapter(accounts));
                recyclerView.getAdapter().notifyDataSetChanged();
            });
        }
    }
}

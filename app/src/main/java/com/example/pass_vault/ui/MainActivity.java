package com.example.pass_vault.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.pass_vault.R;
import com.example.pass_vault.ui.dialogs.ExportCSVDialogFragment;
import com.example.pass_vault.ui.dialogs.ImportCSVDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements ImportCSVDialogFragment.ImportCSVDialogListener {

    public static final String IMPORT_CSV = "IMPORT_CSV";
    public static final String EXPORT_CSV = "EXPORT_CSV";
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new NavigationListener());

        frameLayout = findViewById(R.id.fragment_container);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.import_csv_menu_item) {
            (new ImportCSVDialogFragment(frameLayout)).show(getSupportFragmentManager(), IMPORT_CSV);
        } else if (item.getItemId() == R.id.export_csv_menu_item) {
            (new ExportCSVDialogFragment(frameLayout)).show(getSupportFragmentManager(), EXPORT_CSV);
        } else if (item.getItemId() == R.id.exit_menu_item) {
            finishAffinity();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class NavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.add) {
                selectedFragment = new AddFragment();
            } else if (item.getItemId() == R.id.generate_password) {
                selectedFragment = new PasswordGeneratorFragment();
            } else if (item.getItemId() == R.id.delete) {
                selectedFragment = new DeleteFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
            }

            return true;
        }
    }

    @Override
    public void onReturnValue(boolean isDismissed) {
        if (isDismissed) {
            recreate();
        }

        Log.d(TAG, "onReturnValue: " + isDismissed);
    }
}
package com.example.pass_vault.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AccountItem.class}, version = 1)
public abstract class AccountsDatabase extends RoomDatabase {
    private static AccountsDatabase instance;

    private static final int N_THREADS = 1;
    public static final ExecutorService dbWriterExecutor = Executors.newFixedThreadPool(N_THREADS);

    public abstract AccountItemDao accountItemDao();

    public static synchronized AccountsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AccountsDatabase.class, "accounts_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}

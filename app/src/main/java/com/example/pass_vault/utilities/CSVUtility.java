package com.example.pass_vault.utilities;

import android.content.Context;
import android.util.Log;

import com.example.pass_vault.model.AccountItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVUtility {
    private static final String TAG = "CSVUtility";
    private static final String FILE_NAME = "accounts.csv";

    public static void write(ArrayList<AccountItem> accounts, Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write("Platform,Username,Password\n");
            for (AccountItem account : accounts) {
                writer.write(String.format("%s\n", account.toString()));
            }

            writer.close();
            writer.flush();

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public static ArrayList<AccountItem> read(Context context) {
        ArrayList<AccountItem> accounts = new ArrayList<>();
        File file = new File(context.getFilesDir(), FILE_NAME);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String row;
            while ((row = reader.readLine()) != null) {
                String[] fields = row.split(",");
                String platform = fields[0];
                String username = fields[1];
                String password = fields[2];

                if (!platform.equals("Platform")) {
                    accounts.add(new AccountItem(platform, username, password));
                }
            }

            reader.close();
        } catch (IOException e) {
            return accounts;
        }

        return accounts;
    }
}

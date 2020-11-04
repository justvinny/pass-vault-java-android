package com.example.pass_vault.utilities;

import com.example.pass_vault.model.AccountItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVUtility {
    private static final String CSV_PATH = "accounts.csv";

    public static void write(ArrayList<AccountItem> accounts) throws IOException {
        FileWriter writer = new FileWriter(CSV_PATH);

        writer.write("Platform,Username,Password\n");
        for (AccountItem account : accounts) {
            writer.write(String.format("%s\n", account.toString()));
        }

        writer.close();
        writer.flush();
    }

    public static ArrayList<AccountItem> read() throws IOException {
        ArrayList<AccountItem> accounts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(CSV_PATH));

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
        return accounts;
    }
}

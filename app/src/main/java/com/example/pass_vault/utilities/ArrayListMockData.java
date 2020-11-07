package com.example.pass_vault.utilities;

import com.example.pass_vault.model.AccountItem;

import java.util.ArrayList;

public class ArrayListMockData {
    public static void load(ArrayList<AccountItem> accounts) {
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
}

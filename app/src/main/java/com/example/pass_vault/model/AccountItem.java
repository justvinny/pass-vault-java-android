package com.example.pass_vault.model;

public class AccountItem {
    private String platform;
    private String username;
    private String password;

    public AccountItem(String platform, String username, String password) {
        this.platform = platform;
        this.username = username;
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", platform, username, password);
    }
}

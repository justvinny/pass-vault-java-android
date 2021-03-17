package com.example.pass_vault.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "account_item_table")
public class AccountItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String platform;
    private String username;
    private String password;

    public AccountItem(String platform, String username, String password) {
        this.platform = platform;
        this.username = username;
        this.password = password;
        mustNotBeEmpty();
    }

    public void setId(int id) {
        this.id = id;
    };

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountItem that = (AccountItem) o;
        return platform.toLowerCase().equals(that.platform.toLowerCase()) &&
                username.toLowerCase().equals(that.username.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, username, password);
    }

    private void mustNotBeEmpty() {
        if (platform.isEmpty() || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Fields must not be empty!");
        }
    }
}

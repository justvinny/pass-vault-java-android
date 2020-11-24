package com.example.pass_vault.utilities;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

public class MenuItemTextColorUtility {
    public static void applyDarkText(MenuItem item) {
        String title = item.getTitle().toString();
        SpannableString spannableString = new SpannableString(title);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length(), 0);
        item.setTitle(spannableString);
    }
}

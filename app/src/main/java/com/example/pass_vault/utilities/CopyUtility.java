package com.example.pass_vault.utilities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class CopyUtility {
    public static void copy(String text, Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(text, text);
        clipboard.setPrimaryClip(clip);
    }
}

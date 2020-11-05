package com.example.pass_vault.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

public class KeyboardUtility {
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow((null == activity.getCurrentFocus()) ? null :
                activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

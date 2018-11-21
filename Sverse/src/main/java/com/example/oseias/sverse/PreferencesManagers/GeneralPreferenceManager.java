package com.example.oseias.sverse.PreferencesManagers;

import android.content.Context;
import android.content.SharedPreferences;

public class GeneralPreferenceManager {
    public static final String LAST_INDEX_MAIN_FRAGMENT = "lastIndexMainFragment";

    public void storeInt(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences("MyGeneralPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(Context context, String key, int defaultValue){
        return context.getSharedPreferences("MyGeneralPrefs", Context.MODE_PRIVATE).getInt(key, defaultValue);
    }
}

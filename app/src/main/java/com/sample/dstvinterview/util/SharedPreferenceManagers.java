package com.sample.dstvinterview.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceManagers {

    private SharedPreferences mPreferenceManager;

    public SharedPreferenceManagers(Context context) {
        this.mPreferenceManager =  PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setString(String key, String value) {
        mPreferenceManager.
                edit()
                .putString(key, value)
                .apply();
    }

    public String getString(String key) {
        return mPreferenceManager.getString(key, "");
    }

    public void clear() {
        mPreferenceManager.edit().clear().apply();
    }
}

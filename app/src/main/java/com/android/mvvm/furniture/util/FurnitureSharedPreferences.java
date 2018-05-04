package com.android.mvvm.furniture.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class FurnitureSharedPreferences {

    private SharedPreferences mSharedPreferences;

    @Inject
    public FurnitureSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putData(String key, int data) {
        mSharedPreferences.edit().putInt(key,data).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key,0);
    }
}

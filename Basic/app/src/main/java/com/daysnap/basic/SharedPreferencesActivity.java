package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        PreferenceManager
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", "张三");
        editor.putInt("age", 18);
        editor.apply();
    }
}
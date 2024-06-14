package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LifeCycleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test);
    }
}
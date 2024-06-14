package com.daysnap.jetpckstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.daysnap.jetpckstudy.ui.MyChronometer;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        MyChronometer chronometer = findViewById(R.id.cm_time);
        getLifecycle().addObserver(chronometer);
    }
}
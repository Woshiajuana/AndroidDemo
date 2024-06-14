package com.daysnap.jetpckstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daysnap.jetpckstudy.service.MyLocationService;

public class LifecycleServiceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_service);

        findViewById(R.id.btn_start).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_start) {
            startService(new Intent(this, MyLocationService.class));
        } else if (id == R.id.btn_stop) {
            stopService(new Intent(this, MyLocationService.class));
        }
    }
}
package com.daysnap.jetpckstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_lifecycle).setOnClickListener(this);
        findViewById(R.id.btn_lifecycle_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_lifecycle) {
            Intent intent = new Intent(this, LifecycleActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_lifecycle_service) {
            Intent intent = new Intent(this, LifecycleServiceActivity.class);
            startActivity(intent);
        }
    }
}
package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.daysnap.basic.receiver.AlarmReceiver;

public class AlarmManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        findViewById(R.id.btn_alarm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_alarm) {
            alarmReceiver.sendAlarm();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        alarmReceiver = new AlarmReceiver(this);
        IntentFilter filter = new IntentFilter(AlarmReceiver.ALARM_ACTION);
        registerReceiver(alarmReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(alarmReceiver);
    }
}
package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.daysnap.basic.receiver.NetworkReceiver;
import com.daysnap.basic.receiver.TimeReceiver;

public class SystemBroadcastActivity extends AppCompatActivity {

    private TimeReceiver timeReceiver;
    private NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_broadcast);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 创建一个分钟变更的广播接收器
        timeReceiver = new TimeReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(timeReceiver, filter);

        // 创建一个网络广播接收器
        networkReceiver = new NetworkReceiver();
//        IntentFilter filter1 = new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED);
        IntentFilter filter1 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkReceiver, filter1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(timeReceiver);
        unregisterReceiver(networkReceiver);
    }
}
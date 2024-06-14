package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.daysnap.basic.receiver.OrderAReceiver;
import com.daysnap.basic.receiver.OrderBReceiver;
import com.daysnap.basic.receiver.StandardReceiver;

public class BroadStandardActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String ORDER_ACTION = "com.daysnap.basic.order";

    private StandardReceiver standardReceiver;
    private OrderAReceiver orderAReceiver;
    private OrderBReceiver orderBReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_standard);

        findViewById(R.id.btn_send_standard).setOnClickListener(this);
        findViewById(R.id.btn_send_order).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_send_standard) {
            sendBroadcast();
        } else if (id == R.id.btn_send_order) {
            sendOrderBroadcast();
        }
    }

    private void sendBroadcast () {
        // 发送标准广播
        Intent intent = new Intent(StandardReceiver.STANDARD_ACTION);
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        standardReceiver = new StandardReceiver();
        // 创建一个意图过滤器，只处理 STANDARD_ACTION 广播
        IntentFilter filter = new IntentFilter(StandardReceiver.STANDARD_ACTION);
        registerReceiver(standardReceiver, filter);


        // 多个接收器处理有序广播的顺序规则为：
        // 1. 优先级越大的接收器，越早收到有序广播
        // 2. 优先级相同的时候，越早注册的接收器越早收到广播
        orderAReceiver = new OrderAReceiver();
        IntentFilter filterA = new IntentFilter(ORDER_ACTION);
        filterA.setPriority(8); // 设置优先级
        registerReceiver(orderAReceiver, filterA);

        orderBReceiver = new OrderBReceiver();
        IntentFilter filterB = new IntentFilter(ORDER_ACTION);
        filterB.setPriority(10); // 设置优先级
        registerReceiver(orderBReceiver, filterB);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 注销接收器
        unregisterReceiver(standardReceiver);
        unregisterReceiver(orderAReceiver);
        unregisterReceiver(orderBReceiver);
    }

    private void sendOrderBroadcast () {
        Intent intent = new Intent(ORDER_ACTION);
        sendOrderedBroadcast(intent, null);
    }
}
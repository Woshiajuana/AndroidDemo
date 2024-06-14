package com.daysnap.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.daysnap.basic.BroadStandardActivity;

public class OrderAReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && BroadStandardActivity.ORDER_ACTION.equals(intent.getAction())) {
            Log.d("接受者 => A", "收到广播");
        }
    }
}

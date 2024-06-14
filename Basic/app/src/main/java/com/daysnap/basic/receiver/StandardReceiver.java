package com.daysnap.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.daysnap.basic.utils.ToastUtil;

public class StandardReceiver extends BroadcastReceiver {

    public static final String STANDARD_ACTION = "com.daysnap.basic.standard";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 一旦接受到标准广播，马上触发接收器的 onReceive 方法
        if (intent != null && STANDARD_ACTION.equals(intent.getAction())) {
            Log.d("广播 => ", "收到广播");
        }
    }
}

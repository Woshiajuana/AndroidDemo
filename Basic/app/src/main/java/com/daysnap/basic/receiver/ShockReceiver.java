package com.daysnap.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

import com.daysnap.basic.BroadStandardActivity;

public class ShockReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && BroadStandardActivity.SHOCK_ACTION.equals(intent.getAction())) {
            Log.d("收到 => ", "震动");
            // 从系统服务中获取振动管理器
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // 振动500毫秒
            vibrator.vibrate(500);
        }
    }
}
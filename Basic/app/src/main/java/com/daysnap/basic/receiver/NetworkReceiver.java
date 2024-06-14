package com.daysnap.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            // 收到一个网络变更广播
            // 网络大类 MOBILE  / WIFI
            NetworkInfo networkInfo = intent.getParcelableExtra("networkInfo");
            String name = networkInfo.getTypeName();
            Log.d("ds => ", name);
        }
    }
}

package com.daysnap.basic.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String ALARM_ACTION = "com.daysnap.basic.alarm";
    private final Context mContext;

    public AlarmReceiver(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null &&  ALARM_ACTION.equals(intent.getAction())) {
            Log.d("ds => ", "收到闹钟广播");
        }
    }

    // 发送闹钟广播
    public void sendAlarm() {
        Intent intent = new Intent(ALARM_ACTION);
        // 创建一个用于广播的延迟意图
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        // android 6.0 之后 set 方法在暗屏时不保证发送广播
        // 必须调用 setAndAllowWhileIdle 方法
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);
        }
    }
}

package com.daysnap.basic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class ContentObserverActivity extends AppCompatActivity {

    private SmsGetObserver smsGetObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_observer);

        // 给指定的 Uri 注册内容观察期，一旦发生数据变化，就触发观察器的 onChange 方法
        Uri uri = Uri.parse("content://sms");
        // notifyForDescendents:
        // false 表示精确匹配 即只匹配该 Uri
        // true 表示可以同时匹配其他派生的 Uri
        // 假设 UriMatcher 里注册的 Uri 共有以下类型：
        // 1. content://AUTHORITIES/table
        // 2. content://AUTHORITIES/table/#
        // 3. content://AUTHORITIES/table/subtable
        // 假设我们当前需要观察的 Uri 为 content://AUTHORITIES/table:
        // 如果发生数据变化的 Uri 为 3.
        // 当为false时，监听不到。为 true 则能监听到
        smsGetObserver = new SmsGetObserver(this);
        getContentResolver().registerContentObserver(uri, true, smsGetObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(smsGetObserver);
    }

    private static class SmsGetObserver extends ContentObserver {

        private final Context mContext;

        public SmsGetObserver(Context context) {
            super(new Handler(Looper.getMainLooper()));
            mContext = context;
        }

        @SuppressLint("Range")
        @Override
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);
            // 会调用多次
            if (uri == null) {
                return;
            }
            if (uri.toString().equals("content://sms/raw") || uri.toString().equals("content://sms")) {
                return;
            }
            // 通过内容解析器获取符合条件的结果集游标
            Cursor cursor = mContext.getContentResolver().query(uri, new String[]{"address", "body", "date"}, null, null, "date DESC");
            if (cursor.moveToNext()) {
                // 短信号码
                String sender = cursor.getString(cursor.getColumnIndex("address"));
                // 短信内容
                String content = cursor.getString(cursor.getColumnIndex("body"));
                Log.d("SMS =>", sender + " " + content);
            }
            cursor.close();
        }
    }
}
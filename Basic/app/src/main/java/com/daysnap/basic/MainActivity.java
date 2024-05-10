package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btn_login);
        EditText etUsername = findViewById(R.id.ed_username);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                Log.e("DDD => ", "输入的用户名称" + username);
            }
        });

        // 通知
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 版本兼容
        String channelId = "daysnap";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 创建通知通道（Notification Channel），这是Android 8.0（API 26）及以上版本要求的。
            NotificationChannel channel = new NotificationChannel(
                channelId,
                "消息通知",
                NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        }

        notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("官方通知") // 必须
                .setSmallIcon(R.drawable.baseline_person_24) // 必须
                .setContentText("通知内容通知内容通知内容通知内容") // 设置通知内容
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_1)) // 设置大图标
                .setColor(Color.parseColor("#ff0000")) // 设置小图标颜色
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // 设置点击通知后自动清除通知
                .build();
    }

    public void sendNotification(View view){
        Log.e("xxx => ", "发送通知");
        notificationManager.notify(1, notification);
    }

    public void cancelNotification(View view) {
        notificationManager.cancel(1);
    }

    public void showAlert(View view) {
        @SuppressLint("InflateParams") View dialogView = getLayoutInflater().inflate(R.layout.dialog_view, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("我是对话框")
                .setMessage("今天天气怎么样呀")
                .setView(dialogView)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("xxx => ", "点击了确认");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("xxx => ", "点击了取消");
                    }
                })
                .setNeutralButton("中间", new DialogInterface.OnClickListener() {
                    @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("xxx => ", "点击了中间");
                    }
                })
                .create()
                .show();
    }

    public void showPopupWindow(View view) {
        @SuppressLint("InflateParams") View popupView = getLayoutInflater().inflate(R.layout.dialog_view, null);

        Button btn = popupView.findViewById(R.id.btn);

        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // 设置背景
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        // 相对某个控件的位置（正左下方）无偏移
        popupWindow.showAsDropDown(view);

        // 有偏移
//        popupWindow.showAsDropDown(view, view.getWidth(), view.getHeight());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss(); // 关闭
            }
        });
    }
}
package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            NotificationChannel channel = new NotificationChannel(
                channelId,
                "测试通知",
                NotificationManager.IMPORTANCE_HIGH
            );
        }

        notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("官方通知") // 必须
                .setSmallIcon(R.drawable.baseline_person_24) // 必须
                .setContentText("通知内容通知内容通知内容通知内容")
                .build();
    }

    public void sendNotification(View view){
        notificationManager.notify(1, notification);
    }

    public void cancelNotification() {
        //
    }
}
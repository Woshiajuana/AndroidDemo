package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MetadataActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metadata);

        Button btn = findViewById(R.id.btn_meta);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_meta) {
            // 获取包管理器
            PackageManager pm = getPackageManager();
            try {
                ActivityInfo info =  pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                Bundle bundle = info.metaData;
                String name = bundle.getString("name");
                TextView tv =  findViewById(R.id.tv_meta);
                tv.setText(name);
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
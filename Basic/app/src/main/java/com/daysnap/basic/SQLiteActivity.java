package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPath;
    private String mDatabaseName;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        tvPath = findViewById(R.id .tv_path);
        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);

        // 测试数据库完整路径
        mDatabaseName = getFilesDir() + "/test.db";
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_create) {
            // 创建或打开数据库，如果数据库不存在就创建它，如果存在就打开他
            db = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null);
            String desc = String.format("数据库%s创建%s", db.getPath(), db != null ? "成功" : "失败");
            tvPath.setText(desc);
        } else if (id == R.id.btn_delete) {
            // 删除数据库
            boolean result = deleteDatabase(mDatabaseName);
            String desc = String.format("数据库%s删除%s", db.getPath(), result ? "成功" : "失败");
            tvPath.setText(desc);
        }
    }
}
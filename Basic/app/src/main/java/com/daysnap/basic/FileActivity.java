package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.daysnap.basic.utils.FileUtil;
import com.daysnap.basic.utils.ToastUtil;

import java.io.File;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private String path;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        tvContent = findViewById(R.id.tv_content);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_save) {
            // 保存
            String fileName = System.currentTimeMillis() + ".txt";
            String directory = null;
            // 外部存储的私有空间
            directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();

            // 外部存储的公共空间
//            directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            path = directory + File.separatorChar + fileName;
            StringBuilder sb = new StringBuilder();
            sb.append("姓名：").append("张三");
            sb.append("\n年龄：").append(12);
            FileUtil.saveText(path, sb.toString());
            ToastUtil.show(this, "保存成功");
        } else if (id == R.id.btn_read) {
            String res = FileUtil.openText(path);
            tvContent.setText(res);
            ToastUtil.show(this, "读取成功");
        }
    }
}
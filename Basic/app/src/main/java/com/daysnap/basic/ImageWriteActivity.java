package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.daysnap.basic.utils.FileUtil;
import com.daysnap.basic.utils.ToastUtil;

import java.io.File;

public class ImageWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private String path;
    private ImageView ivContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);

        ivContent = findViewById(R.id.iv_content);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_save) {
            String filename = System.currentTimeMillis() + ".jpg";
            // 获取当前APP的私有下载目录
            String dir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
            path = dir + File.separatorChar + filename;
            // 从指定资源文件中获取位图对象
            Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.yuantu);
            FileUtil.saveImage(path, b1);
            // 回收位图对象
            b1.recycle();
            ToastUtil.show(this, "保存成功");
        } else if (id == R.id.btn_read) {
            // 第1种
//            Bitmap b2 = FileUtil.openImage(path);
            // 第2种
//            Bitmap b2 = BitmapFactory.decodeFile(path);
//            ivContent.setImageBitmap(b2);
            // 第3种
            ivContent.setImageURI(Uri.parse(path));
        }
    }
}
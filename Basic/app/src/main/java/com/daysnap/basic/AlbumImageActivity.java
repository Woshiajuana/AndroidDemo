package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.daysnap.basic.entity.ImageInfo;
import com.daysnap.basic.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

public class AlbumImageActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ImageInfo> mImageList = new ArrayList<>();
    private GridLayout glContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_image);

        glContent = findViewById(R.id.gl_content);

        findViewById(R.id.btn_select).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_select) {

            boolean res = PermissionUtil.checkPermission(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            }, 1);
            if (res) {
                // 手动让 MediaStore 扫描入库
                MediaScannerConnection.scanFile(this, new String[]{
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
                }, null, null);

                loadImageList();
                showImageGrid();
            }
        }
    }

    @SuppressLint("Range")
    private void loadImageList() {
        // 加载图片列表 MediaStore
        String[] columns = new String[]{
                MediaStore.Images.Media._ID, // 编号
                MediaStore.Images.Media.TITLE, // 标题
                MediaStore.Images.Media.SIZE, // 文件大小
                MediaStore.Images.Media.DATA, // 文件路径
        };

        // 图片大小在 300KB 以内
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 307200",
                null,
                "_size DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.id = id;
                imageInfo.title = title;
                imageInfo.size = size;
                imageInfo.path = path;
                mImageList.add(imageInfo);
                Log.d("加载的图片 => ", imageInfo.toString());
            }
            cursor.close();
        }
    }

    private void showImageGrid() {
        glContent.removeAllViews();
        for (ImageInfo image : mImageList) {
            // image > imageView
            ImageView iv = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeFile(image.path);
            iv.setImageBitmap(bitmap);
            // 设置图像视图缩放类型
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // 设置图像视图的布局参数
            int px = Utils.dip2px(this, 110);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px, px);
            iv.setLayoutParams(params);
            // 设置图像视图的内部间距
            int padding = Utils.dip2px(this, 5);
            iv.setPadding(padding, padding, padding, padding);
            glContent.addView(iv);
        }
    }
}
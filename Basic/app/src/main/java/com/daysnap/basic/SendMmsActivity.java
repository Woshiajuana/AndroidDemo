package com.daysnap.basic;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SendMmsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivImage;
    private ActivityResultLauncher<Intent> resultLauncher;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mms);

        ivImage = findViewById(R.id.iv_image);

        findViewById(R.id.btn_select).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);


        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        // 获取选择图片的路径对象
                        picUri = intent.getData();
                        if (picUri != null) {
                            ivImage.setImageURI(picUri);
                        }
                    }

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_select) {
            // 跳转系统相册 选择图片 并返回
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            // 设置内容类型为图片类型
            intent.setType("image/*");
            // 打开相册
            resultLauncher.launch(intent);
        } else if (id == R.id.btn_send) {
            sendMms();
        }
    }

    // 发送带图片的彩信
    private void sendMms () {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // intent 的接受者将被准许读取 Intent 携带的 URI 数据
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        // 彩信发送的目标号码
        intent.putExtra("address", "13199999999");
        // 彩信的标题
        intent.putExtra("subject", "哈哈");
        // 彩信的内容
        intent.putExtra("sms_body", "你好，你吃饭了吗？");
        // 彩信的图片附件
        intent.putExtra(Intent.EXTRA_STREAM, picUri);
        intent.setType("image/*");
        startActivity(intent);
    }
}
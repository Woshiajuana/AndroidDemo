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
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        findViewById(R.id.btn_jump_page).setOnClickListener(this);
        findViewById(R.id.btn_jump_dial).setOnClickListener(this);
        findViewById(R.id.btn_jump_sms).setOnClickListener(this);
        findViewById(R.id.btn_jump_my).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_request).setOnClickListener(this);

        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    // 处理从启动的 Activity 返回的结果
                    Intent intent = result.getData();
                    if (intent != null) {
                        // 在这里使用返回的数据
                        String data = intent.getStringExtra("result");
                        TextView tv = findViewById(R.id.tv_return_result);
                        tv.setText(data);
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_jump_page) {
            // 跳转页面 显示意图跳转
            Intent intent = new Intent(this, ViewPagerActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_jump_dial) {
            // 跳转到拨打电话 隐方式跳转
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:12345");
            intent.setData(uri);
            startActivity(intent);
        } else if (id == R.id.btn_jump_sms) {
            // 跳转到短信页面 隐方式跳转
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("smsto:12345");
            intent.setData(uri);
            startActivity(intent);
        } else if (id == R.id.btn_jump_my) {
            // 跳转到自定义页面 隐方式跳转
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEWPAGER");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        } else if (id == R.id.btn_send) {
            // 跳转传递参数
            Intent intent = new Intent(this, IntentParamsActivity.class);
//            intent.putExtra("name", "张三");
            // 创建包裹
            Bundle bundle = new Bundle();
            bundle.putString("name", "张三");
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.btn_request) {
            // 跳转并接收返回结果
            Intent intent = new Intent(this, IntentParamsActivity.class);
            // 创建包裹
            Bundle bundle = new Bundle();
            bundle.putString("name", "张三");
            intent.putExtras(bundle);
//            startActivityForResult(intent, 1); // 这种写法过期了
            activityLauncher.launch(intent);
        }
    }
}
package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        findViewById(R.id.btn_jump_page).setOnClickListener(this);
        findViewById(R.id.btn_jump_dial).setOnClickListener(this);
        findViewById(R.id.btn_jump_sms).setOnClickListener(this);
        findViewById(R.id.btn_jump_my).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
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
            // 跳转到短信页面
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("smsto:12345");
            intent.setData(uri);
            startActivity(intent);
        } else if (id == R.id.btn_jump_my) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEWPAGER");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        } else if (id == R.id.btn_send) {
            Intent intent = new Intent(this, IntentParamsActivity.class);
            // 创建包裹
            Bundle bundle = new Bundle();
            bundle.putString("name", "张三");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
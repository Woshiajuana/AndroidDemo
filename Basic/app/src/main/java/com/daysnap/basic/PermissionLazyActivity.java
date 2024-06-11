package com.daysnap.basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.daysnap.basic.utils.PermissionUtil;
import com.daysnap.basic.utils.ToastUtil;

public class PermissionLazyActivity extends AppCompatActivity implements View.OnClickListener {

    // 通讯录
    private static final String[] PERMISSIONS_CONTACTS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };

    private static final int REQUEST_CODE_CONTACTS = 1;

    // 短信
    private static final String[] PERMISSION_SMS = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };

    private static final int REQUEST_CODE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);

        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_contact) {
            // 拨打电话
            PermissionUtil.checkPermission(this, PERMISSIONS_CONTACTS, REQUEST_CODE_CONTACTS);
        } else if (id == R.id.btn_sms) {
            // 短信
            PermissionUtil.checkPermission(this, PERMISSION_SMS, REQUEST_CODE_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (PermissionUtil.checkGrant(grantResults)) {
                ToastUtil.show(this, "通讯录权限获取成功");
            } else {
                ToastUtil.show(this, "获取通讯录读写权限失败");
            }
        } else if (requestCode == REQUEST_CODE_SMS) {
            if (PermissionUtil.checkGrant(grantResults)) {
                ToastUtil.show(this, "收发短信权限获取成功");
            } else {
                ToastUtil.show(this, "收发短信获取权限失败");
            }
        }
    }
}
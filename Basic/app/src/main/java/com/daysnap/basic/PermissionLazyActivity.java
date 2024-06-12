package com.daysnap.basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.daysnap.basic.utils.PermissionUtil;
import com.daysnap.basic.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            // 手机通讯录
            boolean res = PermissionUtil.checkPermission(this, PERMISSIONS_CONTACTS, REQUEST_CODE_CONTACTS);
            if (res) {
                addContacts();
            }
        } else if (id == R.id.btn_sms) {
            // 短信
            PermissionUtil.checkPermission(this, PERMISSION_SMS, REQUEST_CODE_SMS);
        }
    }

    // 往手机通讯录添加一个联系人信息（姓名、电话号码、电子邮箱）
    private void addContacts() {
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        // 往 raw_contracts 添加联系人记录 并获取添加后的联系人编号
        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(uri);

        ContentValues name = new ContentValues();
        // 关联联系人编号
        name.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // 姓名的数据类型
        name.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人的姓名
        name.put(ContactsContract.Contacts.Data.DATA2, "张三");
        resolver.insert(ContactsContract.Data.CONTENT_URI, name);

        ContentValues phone = new ContentValues();
        // 关联联系人编号
        phone.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // 姓名的数据类型
        phone.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 联系人的手机号
        phone.put(ContactsContract.Contacts.Data.DATA1, "13199999999");
        // 联系类型 1. 表示家庭 2表示工作
        phone.put(ContactsContract.Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        resolver.insert(ContactsContract.Data.CONTENT_URI, phone);
    }

    // 方式二 批处理方式
    // 每一次操作都是一个 ContentProviderOperation 构建
    // 一个操作集合，然后一次性执行，好处就是要么全部成功，要么
    // 全部失败，保证了事务的一致性
    private void addFullContacts() {
        ContentResolver resolver = getContentResolver();
        ContentProviderOperation opMain = ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build();

        ContentProviderOperation opName = ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                // 将第0个操作的id 作为 data 表 中的RAW_CONTACT_ID
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .build();

        // todo 没写完大概思路
        // 声明一个内容操作器的列表，并将上面四个操作器添加到该列表中
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        operations.add(opMain);
        operations.add(opName);
        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // 查询通讯录信息
    @SuppressLint("Range")
    private void readPhoneContacts() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{ContactsContract.RawContacts._ID}, null, null, null, null);
        while (cursor.moveToNext()) {
            int rawContractId = cursor.getInt(0);
            Uri uri = Uri.parse("content://com.android.contracts/contracts/" + rawContractId + "/data");
            Cursor dataCursor = resolver.query(uri, new String[]{ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.Contacts.Data.DATA1, ContactsContract.Contacts.Data.DATA2}, null, null, null, null);
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Contacts.Data.DATA1));
                String mimeType = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Contacts.Data.MIMETYPE));
                if (Objects.equals(mimeType, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
                    // 是姓名  todo
                    Log.d("readPhoneContacts 是姓名 => ", data1);
                } else if (Objects.equals(mimeType, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
                    // 是手机 todo
                    Log.d("readPhoneContacts 是手机 => ", data1);
                }
            }
            dataCursor.close();
        }
        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (PermissionUtil.checkGrant(grantResults)) {
                ToastUtil.show(this, "通讯录权限获取成功");
            } else {
                ToastUtil.show(this, "获取通讯录读写权限失败");
                jumpToSettings();
            }
        } else if (requestCode == REQUEST_CODE_SMS) {
            if (PermissionUtil.checkGrant(grantResults)) {
                ToastUtil.show(this, "收发短信权限获取成功");
            } else {
                ToastUtil.show(this, "收发短信获取权限失败");
                jumpToSettings();
            }
        }
    }

    // 跳转到应用设置界面
    private void jumpToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.daysnap.basic.database.UserDBHelper;
import com.daysnap.basic.entity.User;
import com.daysnap.basic.utils.ToastUtil;

import java.util.List;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etAge;
    private EditText etHeight;
    private EditText etWeight;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获取数据库帮助实例
        mHelper = UserDBHelper.getInstance(this);
        mHelper.openWriteLink();
        mHelper.openReadLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_insert) {
            // 插入数据
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String height = etHeight.getText().toString();
            String weight = etWeight.getText().toString();
            int gender = rbMale.isChecked() ? 1 : 2;
            User user = new User(
                    name,
                    Integer.parseInt(age),
                    Long.parseLong(height),
                    Float.parseFloat(weight),
                    gender
            );
            if (mHelper.insert(user) > -1) {
                ToastUtil.show(this, "添加成功");
            }
        } else if (id == R.id.btn_delete) {
            // 删除数据
            String name = etName.getText().toString();
            if (mHelper.deleteByName(name) > 0) {
                ToastUtil.show(this, "删除成功");
            }
        } else if (id == R.id.btn_update) {
            // 修改数据
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String height = etHeight.getText().toString();
            String weight = etWeight.getText().toString();
            int gender = rbMale.isChecked() ? 1 : 2;
            User user = new User(
                    name,
                    Integer.parseInt(age),
                    Long.parseLong(height),
                    Float.parseFloat(weight),
                    gender
            );
            if (mHelper.update(user) > 0) {
                ToastUtil.show(this, "修改成功");
            }
        } else if (id == R.id.btn_query) {
            // 查询数据
            List<User> list =  mHelper.queryAll();
            for (User u: list) {
                Log.d("ning", u.toString());
            }
        }
    }
}
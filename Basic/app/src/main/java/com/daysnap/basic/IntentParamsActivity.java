package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntentParamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_params);

        TextView tv = findViewById(R.id.tv_content);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        tv.setText(name);

        Button btn =  findViewById(R.id.btn_response);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                // 添加你想要返回的数据，使用putExtra方法
                returnIntent.putExtra("result", "我是张三，你好呀！");
                // 设置返回的结果码和数据
                setResult(RESULT_OK, returnIntent);
                // 结束这个Activity
                finish();
            }
        });
    }
}
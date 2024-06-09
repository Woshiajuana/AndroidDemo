package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}
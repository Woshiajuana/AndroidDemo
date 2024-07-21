package com.ohshell.customview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ohshell.customview.R;
import com.ohshell.customview.components.NumberInputView;

public class NumberInputActivity extends AppCompatActivity {

    private String TAG = "NumberInputActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_input);

        NumberInputView niNumber = findViewById(R.id.ni_number);
        niNumber.setOnValueChangeListener(value -> {
            Log.d(TAG, "value => " + value);
        });
    }
}
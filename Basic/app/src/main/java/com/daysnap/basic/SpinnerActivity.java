package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends AppCompatActivity {

    private Spinner spinner;
    private final static String[] starArray = {
            "水星",
            "金星",
            "地球",
            "火星",
            "木星",
            "土星"
    };
    private Spinner spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = findViewById(R.id.sp_dropdown);
        // 声明一个适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, starArray);

        spinner.setAdapter(starAdapter);
        // 设置下拉框默认显示第一项
        spinner.setSelection(0);
        // 设置监听点击了哪一项
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDialog = findViewById(R.id.sp_dropdown_dialog);
        spinnerDialog.setAdapter(starAdapter);
        spinnerDialog.setPrompt("请选择行星");
        spinnerDialog.setSelection(0);
    }
}
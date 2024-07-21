package com.ohshell.customview.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ohshell.customview.R;

public class NumberInputView extends RelativeLayout {

    private int value = 0;
    private View btnMinus;
    private View btnPlus;
    private EditText etValue;

    public NumberInputView(Context context) {
        this(context, null);
    }

    public NumberInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化 view
        initView(context);

        // 初始化事件
        initEvent();
    }
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_number_input, this, true);

        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);
        etValue = findViewById(R.id.et_value);
    }

    private void initEvent() {
        btnMinus.setOnClickListener(v -> {
            setValue(value - 1);
        });

        btnPlus.setOnClickListener(v -> {
            setValue(value + 1);
        });
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        etValue.setText(String.valueOf(value));
    }

}

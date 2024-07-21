package com.ohshell.customview.components;

import android.content.Context;
import android.content.res.TypedArray;
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
    private OnValueChangeListener onValueChangeListener;

    private int min;
    private int max;
    private int defaultValue;
    private int step;
    private boolean disabled;

    public NumberInputView(Context context) {
        this(context, null);
    }

    public NumberInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化属性
        initAttrs(context, attrs);

        // 初始化 view
        initView(context);

        // 初始化事件
        initEvent();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.NumberInputView);
        min = a.getInt(R.styleable.NumberInputView_min, Integer.MIN_VALUE);
        max = a.getInt(R.styleable.NumberInputView_max, Integer.MAX_VALUE);
        defaultValue = a.getInt(R.styleable.NumberInputView_defaultValue, 0);
        step = a.getInt(R.styleable.NumberInputView_step, 1);
        disabled = a.getBoolean(R.styleable.NumberInputView_disabled, false);
        a.recycle();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_number_input, this, true);

        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);
        etValue = findViewById(R.id.et_value);

        setValue(defaultValue);
    }

    private void initEvent() {
        btnMinus.setOnClickListener(v -> {
            int val = value - step;
            if (val < min) {
                val = min;
            }
            setValue(val);
        });

        btnPlus.setOnClickListener(v -> {
            int val = value + step;
            if (val > max) {
                val = max;
            }
            setValue(val);
        });
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        etValue.setText(String.valueOf(value));

        setDisabled(disabled);

        if (onValueChangeListener != null) {
            onValueChangeListener.onChange(value);
        }
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.onValueChangeListener = listener;
    }
    public interface OnValueChangeListener {
        void onChange(int value);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
        if (disabled) {
            btnMinus.setEnabled(false);
            btnPlus.setEnabled(false);
        } else {
            btnMinus.setEnabled(value > min);
            btnPlus.setEnabled(max > value);
        }
    }
}

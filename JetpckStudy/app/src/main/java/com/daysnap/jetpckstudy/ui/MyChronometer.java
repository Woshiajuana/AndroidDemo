package com.daysnap.jetpckstudy.ui;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyChronometer extends Chronometer implements DefaultLifecycleObserver {
    private long elapsedTime = 0;

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }
}

package com.daysnap.jetpckstudy.service;

import androidx.lifecycle.LifecycleService;

public class MyLocationService extends LifecycleService {
    public MyLocationService () {
        MyLocationObserver observer = new MyLocationObserver(this);
        getLifecycle().addObserver(observer);
    }
}

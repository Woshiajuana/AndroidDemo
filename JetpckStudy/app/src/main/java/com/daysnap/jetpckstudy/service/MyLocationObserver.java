package com.daysnap.jetpckstudy.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyLocationObserver implements DefaultLifecycleObserver {

    private Context mContext;
    private LocationManager locationManager;
    private MyLocationListener locationListener;

    public MyLocationObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d("ds => ", "开始监听定位0");
        startGetLocation();
    }

    private void startGetLocation() {
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        Log.d("ds => ", "开始监听定位");
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("ds => ", "开始监听定位1");
            return;
        }
        Log.d("ds => ", "开始监听定位2");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1, locationListener);
    }

    private void stopGetLocation() {
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        stopGetLocation();
    }

    static class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Log.d("ds => 位置发送改变", location.toString());
        }
    }

}

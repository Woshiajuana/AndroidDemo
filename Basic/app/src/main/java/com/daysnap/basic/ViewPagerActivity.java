package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View v1 = layoutInflater.inflate(R.layout.view_pager_layout1, null);
        @SuppressLint("InflateParams") View v2 = layoutInflater.inflate(R.layout.view_pager_layout2, null);
        @SuppressLint("InflateParams") View v3 = layoutInflater.inflate(R.layout.view_pager_layout3, null);

        List<View> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v3);

        ViewPager viewPager = findViewById(R.id.vp);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(list);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
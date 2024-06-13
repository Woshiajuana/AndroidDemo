package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import com.daysnap.basic.adapter.ViewPager2Adapter;
import com.daysnap.basic.bean.Planet;

import java.util.List;

public class PagerTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab);

        initPagerStrip();
        initViewPager();
    }

    // 初始化翻页标签栏
    private void initPagerStrip() {
        PagerTabStrip ptsTab = findViewById(R.id.pts_tab);
        ptsTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        ptsTab.setTextColor(Color.BLACK);
    }

    // 初始化翻页标签视图
    private void initViewPager() {
        ViewPager vpContent = findViewById(R.id.vp_content);
        List<Planet> planetList = Planet.getDefaultList();
        ViewPager2Adapter viewPagerAdapter = new ViewPager2Adapter(this, planetList);
        vpContent.setAdapter(viewPagerAdapter);
    }
}
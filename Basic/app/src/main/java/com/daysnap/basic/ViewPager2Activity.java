package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.daysnap.basic.adapter.ViewPager2Adapter;
import com.daysnap.basic.bean.Planet;

import java.util.List;

public class ViewPager2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        ViewPager vpContent = findViewById(R.id.vp_content);
        List<Planet> planetList = Planet.getDefaultList();
        ViewPager2Adapter viewPagerAdapter = new ViewPager2Adapter(this, planetList);
        vpContent.setAdapter(viewPagerAdapter);

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 在翻页过程中触发
                // position 当前页面的序号
                // positionOffset 表示页面偏移的百分比 取值 0 ~ 1
                // positionOffsetPixels 偏移距离
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 翻页状态改变时触发
                // state 取值说明 0 静止  1 正在滑动  2 滑动完毕
                // 在翻页过程中，状态值变化依次为：正在滑动 -> 滑动完毕 -> 静止
            }
        });
    }
}
package com.daysnap.basic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daysnap.basic.R;
import com.daysnap.basic.ViewPager2Activity;
import com.daysnap.basic.bean.Planet;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPager2Adapter extends PagerAdapter {

    private final Context mContext;
    private final List<Planet> mPlanetList;

    public ViewPager2Adapter(Context context, List<Planet> planetList) {
        mContext = context;
        mPlanetList = planetList;
    }

    @Override
    public int getCount() {
        return mPlanetList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    // 初始化
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView view = (ImageView) LayoutInflater.from(mContext).inflate(R.layout.item_view_pager2, null);
        Planet planet = mPlanetList.get(position);
        view.setImageResource(planet.image);
        container.addView(view);
        return view;
    }

    // 从容器中销毁指定位置的页面
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // todo 这里可能有问题
        container.removeAllViews();
    }
}

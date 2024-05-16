package com.daysnap.basic;


import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    private final List<View> mListView;

    public ViewPagerAdapter(List<View> mListView) {
        this.mListView = mListView;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 1 将给定位置的 view 添加到 ViewGroup 容器中，创建并显示出来
        // 2 返回一个代表新增页面的 Object(key) 通常都是直接返回 view 本身就可以了
        // 当然也可以自定义 key，但是 key 和每个 view 要一一对应的关系
        View view = mListView.get(position);
        container.addView(view);
        return view;
    }


    @Override
    public int getCount() {
        return mListView.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 判断 instantiateItem 函数返回来的 key 与一个页面的视图是否代表同一个视图
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 移出一个给定位置的页面 适配器有责任从容器中删除这个视图
        // 这是为了确保在 finishUpdate(viewGroup) 返回时视图能够被移出
        container.removeView(mListView.get(position));
    }
}

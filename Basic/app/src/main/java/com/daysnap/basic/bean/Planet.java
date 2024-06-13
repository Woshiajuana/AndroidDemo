package com.daysnap.basic.bean;

import com.daysnap.basic.R;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    public int image; // 行星图标
    public String name; // 行星名称
    public String desc; // 行星描述

    public Planet(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    public static List<Planet> getDefaultList () {
        List<Planet> planetList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            planetList.add(new Planet(R.drawable.ic_1, "张三" + i, "描述" + i));
        }
        return planetList;
    }
}

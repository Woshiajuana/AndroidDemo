package com.daysnap.basic.entity;

import androidx.annotation.NonNull;

public class User {
    public int id; // 序号
    public String name; // 姓名
    public int age; // 年龄
    public long height; // 身高
    public float weight; // 体重
    public int gender; // 性别

    public User () {

    }

    public User(String name, int age, long height, float weight, int gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", gender=" + gender +
                '}';
    }
}

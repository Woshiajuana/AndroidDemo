package com.owulia.jsondemo;

import com.google.gson.annotations.SerializedName;

public class Student {
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @SerializedName("name") //
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

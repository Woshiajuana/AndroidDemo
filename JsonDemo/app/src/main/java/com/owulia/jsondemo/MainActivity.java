package com.owulia.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student("Jack", 20);
        Gson gson = new Gson();
        String jsonStudent1 = gson.toJson(student);

        Student student1 = gson.fromJson(jsonStudent1, Student.class);

        Student[] arrStudents = { student, student1 };
        String jsonArrStudents = gson.toJson(arrStudents);

        Student[] arrStudents1 = gson.fromJson(jsonArrStudents, Student[].class);

        List<Student> list = Arrays.asList(arrStudents1);

        List<Student> students = new ArrayList<>();
        students = gson.fromJson(jsonArrStudents, List.class);
    }
}

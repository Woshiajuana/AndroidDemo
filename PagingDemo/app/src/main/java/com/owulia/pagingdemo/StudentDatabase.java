package com.owulia.pagingdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase instance;

    static synchronized StudentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, StudentDatabase.class, "student_table")
                    .build();
        }
        return instance;
    }

    abstract StudentDao getStudentDao();

}

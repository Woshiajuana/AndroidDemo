package com.owulia.mvvmdemo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.owulia.mvvmdemo.utils.Constants

class UserDatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(
    context,
    Constants.DB_NAME,
    null,
    Constants.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        println("创建表")
        // 创建数据库
        val sql = "create table user(_id integer primary key autoincrement, userName varchar(30), password varchar(30), sex varchar(5), age integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
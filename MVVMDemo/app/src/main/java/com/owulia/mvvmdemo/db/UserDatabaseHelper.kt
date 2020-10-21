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
        val sql = "create table ${Constants.DB_TABLE_NAME}(${Constants.FILED_ID} integer primary key autoincrement, ${Constants.FILED_USER_NAME} varchar(30), ${Constants.FILED_PASSWORD} varchar(30), ${Constants.FILED_SEX} varchar(5), ${Constants.FILED_AGE} integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
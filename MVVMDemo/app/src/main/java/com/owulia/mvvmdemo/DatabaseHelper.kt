package com.owulia.mvvmdemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(
    context,  // 上下文
    Constants.DATABASE_NAME, // 数据库名称
    null, // 游标工厂
    Constants.VERSION_CODE // 版本号
) {
    override fun onCreate(db: SQLiteDatabase?) {
//        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        TODO("Not yet implemented")
    }
}
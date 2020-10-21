package com.owulia.mvvmdemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.owulia.mvvmdemo.utils.Constants

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(
    context,  // 上下文
    Constants.DATABASE_NAME, // 数据库名称
    null, // 游标工厂
    Constants.VERSION_CODE // 版本号
) {
    // 创建时候的回调，第一次创建数据库的时候被调用
    override fun onCreate(db: SQLiteDatabase?) {
        println("创建数据库...")
        // 创建表
        // sql: create table table_name(_id integer, name varchar, age integer, salary integer);
        val sql = "create table ${Constants.TABLE_NAME}(_id integer, name varchar, age integer, salary integer, phone integer, address varchar)"
        db?.execSQL(sql)
    }

    // 升级数据库的回调
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("升级数据库...")
        // sql: alter table table_name add phone integer
        when (oldVersion) {
            1 -> {
                val sql = "alter table ${Constants.TABLE_NAME} add phone integer"
                db?.execSQL(sql)
            }
            2 -> {
                val sql = "alter table ${Constants.TABLE_NAME} add address varchar"
                db?.execSQL(sql)
            }
            3 -> {
                val sql = "alter table ${Constants.TABLE_NAME} add phone integer"
                db?.execSQL(sql)
            }
        }

    }
}
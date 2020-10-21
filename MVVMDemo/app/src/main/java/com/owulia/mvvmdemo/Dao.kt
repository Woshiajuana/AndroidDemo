package com.owulia.mvvmdemo

import android.annotation.SuppressLint
import android.content.Context

/**
 * 操作数据库的增删改查
 * */
class Dao constructor(context: Context) {

    val helper = DatabaseHelper(context)

    fun insert () {
        val db = helper.writableDatabase
        val sql = "insert into ${Constants.TABLE_NAME}(_id, name, age, salary, phone, address) values(?,?,?,?,?,?)"
        db.execSQL(sql, arrayOf(1, "BillGates", 60, 1, 110, "USA"))
        db.close()
    }

    fun delete () {
        val db = helper.writableDatabase
        val sql = "delete from ${Constants.TABLE_NAME} where age = 60"
        db.execSQL(sql)
        db.close()
    }

    fun update () {
        val db = helper.writableDatabase
        val sql = "update ${Constants.TABLE_NAME} set salary = 2 where age = 60"
        db.execSQL(sql)
        db.close()
    }

    fun query () {
        val db = helper.writableDatabase
        val sql = "select * from ${Constants.TABLE_NAME}"
        val cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()) {
            val index = cursor.getColumnIndex("name")
            val name = cursor.getString(index)
            println("name => $name")
        }
        cursor.close()
        db.close()
    }

}
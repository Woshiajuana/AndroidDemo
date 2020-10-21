package com.owulia.mvvmdemo

import android.content.ContentValues
import android.content.Context
import com.owulia.mvvmdemo.utils.Constants

/**
 * 操作数据库的增删改查
 * */
class Dao constructor(context: Context) {

    val helper = DatabaseHelper(context)

    fun insert() {
        val db = helper.writableDatabase
//        val sql = "insert into ${Constants.TABLE_NAME}(_id, name, age, salary, phone, address) values(?,?,?,?,?,?)"
//        db.execSQL(sql, arrayOf(1, "BillGates", 60, 1, 110, "USA"))
        // 添加数据
        val values = ContentValues()
        values.apply {
            put("_id", 2)
            put("name", "larrypage")
            put("salary", 1)
            put("phone", 1290)
            put("address", "USA")
        }
        db.insert(Constants.TABLE_NAME, null, values)

        db.close()
    }

    fun delete() {
        val db = helper.writableDatabase
//        val sql = "delete from ${Constants.TABLE_NAME} where age = 60"
//        db.execSQL(sql)

        db.delete(Constants.TABLE_NAME, null, null)
        db.close()
    }

    fun update() {
        val db = helper.writableDatabase
//        val sql = "update ${Constants.TABLE_NAME} set salary = 2 where age = 60"
//        db.execSQL(sql)

        val values = ContentValues()
        values.put("phone", 123456789)
        db.update(Constants.TABLE_NAME, values, null, null)
        db.close()
    }

    fun query() {
        val db = helper.writableDatabase
//        val sql = "select * from ${Constants.TABLE_NAME}"
//        val cursor = db.rawQuery(sql, null)
//        while (cursor.moveToNext()) {
//            val index = cursor.getColumnIndex("name")
//            val name = cursor.getString(index)
//            println("name => $name")
//        }
//        cursor.close()

        val cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null)
        while(cursor.moveToNext()) {
            val index = cursor.getColumnIndex("name")
            val name = cursor.getString(index)
            println("name => $name")
        }
        cursor.close()
        db.close()


//        db.beginTransaction()
//        try {
//            db.execSQL("update user set money = 10000 where name = 'a'")
//            // 这里出现异常
//            db.execSQL("update user set money = 10000 where name = 'b'")
//            db.setTransactionSuccessful()
//        } catch (e : Exception) {
//
//        } finally {
//            db.endTransaction()
//            db.close()
//        }
    }

}
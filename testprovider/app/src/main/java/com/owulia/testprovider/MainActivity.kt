package com.owulia.testprovider

import android.content.ContentValues
import android.database.ContentObserver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("content://com.owulia.mvvmdemo/user")
        contentResolver.registerContentObserver(uri, true, object : ContentObserver(Handler()){
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                println("数据发生了变化")
            }
        })


        /**
         * 获取数据
         * */
        vButton.setOnClickListener {
            val cursor = contentResolver.query(uri, null, null, null, null, null)
            val columnNames = cursor?.columnNames
            while (cursor?.moveToNext() == true) {
                columnNames?.forEach {
                    val value = cursor.getString(cursor.getColumnIndex(it))
                    println("name => $it value => $value")
                }
            }
            cursor?.close()
        }

        /**
         * 插入数据
         * */
        vAddButton.setOnClickListener {
            val values = ContentValues().apply {
                put("userName", "陈1")
                put("sex", "nan")
                put("age", 1)
                put("password", "123456")
            }
            contentResolver.insert(uri, values)
        }
    }
}

package com.owulia.testprovider

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.CalendarContract
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

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

        /**
         * 插入日历数据
         * */
        vAddCalendarButton.setOnClickListener {
            checkCalendarPermission()
        }
    }

    /**
     * 检查权限
     * */
    private fun checkCalendarPermission () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val redPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR)
            val writePermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR)
            if (redPermission == PackageManager.PERMISSION_GRANTED && writePermission == PackageManager.PERMISSION_GRANTED) {
                // 有权限
                insertCalendar()
            } else {
                // 没有权限
                // 做个提示，用户点击了确定了之后再去请求调用权限
                // 如果点击了不再提示，就不再获取了
                // 如果不能使用则直接退出
                requestPermissions(arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        println("onRequestPermissionsResult => requestCode = $requestCode")
        println("onRequestPermissionsResult => permissions = $permissions")
        println("onRequestPermissionsResult => grantResults = ${grantResults[0]}")
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 有权限
                insertCalendar()
            } else {
                // 没权限
//                finish()
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun getCalendar () {
//        val uri = Uri.parse("content://com.android.calendar/calendars")
        val uri = CalendarContract.Calendars.CONTENT_URI
        val cursor = contentResolver.query(uri,null, null, null, null, null)
        val columnNames = cursor?.columnNames
        while (cursor?.moveToNext() == true) {
            println("===========================")
            columnNames?.forEach {
                val value = cursor.getString(cursor.getColumnIndex(it))
                println("Name: $it  || Value: $value")
            }
            println("===========================")
        }
        cursor?.close()
    }

    @SuppressLint("MissingPermission")
    private fun insertCalendar () {
        val calID = 1
        val beginTime = Calendar.getInstance()
        // 年 月(从0开始) 日 时 分
        beginTime.set(2020, 10, 11, 0, 0)
        val beginTimeMills = beginTime.timeInMillis
        // 结束时间
        val endTime = Calendar.getInstance()
        endTime.set(2020, 10,11, 23, 59)
        val endTimeTimeMills = endTime.timeInMillis
        // 事件内容
        // 时区
        val timeZone = TimeZone.getDefault().id
        // 插入数据
        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, beginTimeMills)
            put(CalendarContract.Events.DTEND, endTimeTimeMills)
            put(CalendarContract.Events.CALENDAR_ID, calID)
            put(CalendarContract.Events.EVENT_TIMEZONE, timeZone)
            put(CalendarContract.Events.TITLE, "双十一抢购")
            put(CalendarContract.Events.DESCRIPTION, "尽量把自己想买的东西一口气买完")
            put(CalendarContract.Events.EVENT_LOCATION, "家里")
        }
//        val uri = Uri.parse("content://com.android.calendar/calendars")
        val uri = CalendarContract.Events.CONTENT_URI
        val resultUri = contentResolver.insert(uri, values)
        println("resultUri => $resultUri") // 35


        // 插入提醒
        val eventId = resultUri?.lastPathSegment
        println("eventId => $eventId")
        val reminderValues = ContentValues().apply {
            put(CalendarContract.Reminders.EVENT_ID, eventId)
            put(CalendarContract.Reminders.MINUTES, 15)
            put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
        }
        val reminderUri = CalendarContract.Reminders.CONTENT_URI
        val reminderResultUri = contentResolver.insert(reminderUri, reminderValues)
        println("reminderResultUri => $reminderResultUri")
    }
}

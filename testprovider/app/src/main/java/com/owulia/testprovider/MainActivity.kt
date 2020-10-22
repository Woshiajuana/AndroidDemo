package com.owulia.testprovider

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.UriMatcher
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.CalendarContract
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    /**
     * 倒计时
     * */
    val mCountDownTimer = object : CountDownTimer(60 * 1000, 1000) {
        override fun onFinish() {
        }

        override fun onTick(millisUntilFinished: Long) {
            
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
        const val PERMISSION_CONTACTS_REQUEST_CODE = 2
        const val PERMISSION_SMS_REQUEST_CODE = 3
        const val SMS_MATCHER_CODE = 1
        val smsUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI("sms", "inbox/#", SMS_MATCHER_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("content://com.owulia.mvvmdemo/user")
//        contentResolver.registerContentObserver(uri, true, object : ContentObserver(Handler()){
//            override fun onChange(selfChange: Boolean) {
//                super.onChange(selfChange)
//                println("数据发生了变化")
//            }
//        })


        /**
         * 监听短信
         * */
        val smsUri = Uri.parse("content://sms")
        contentResolver.registerContentObserver(smsUri, true, object : ContentObserver(Handler()) {
            override fun onChange(selfChange: Boolean, uri: Uri?) {
                super.onChange(selfChange, uri)
                if (smsUriMatcher.match(uri) == SMS_MATCHER_CODE) {
                    println("监听短信uri => $uri")
                    val cursor = contentResolver.query(uri!!, null, null, null, null)
                    val columnNames = cursor?.columnNames
                    while (cursor?.moveToNext() == true) {
                        println("===========================")
                        columnNames?.forEach {
                            val value = cursor.getString(cursor.getColumnIndex(it))
                            println("Name: $it  <======>  Value: $value")
                        }
                        println("===========================")
                    }
                    cursor?.close()
                }
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

        /**
         * 获取通讯录信息
         * */
        vContactsButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val redPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS)
                if (redPermission == PackageManager.PERMISSION_GRANTED) {
                    // 有权限
                    getContactsInfo()
                } else {
                    // 没有权限
                    // 做个提示，用户点击了确定了之后再去请求调用权限
                    // 如果点击了不再提示，就不再获取了
                    // 如果不能使用则直接退出
                    requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), PERMISSION_CONTACTS_REQUEST_CODE)
                }
            } else {
                getContactsInfo()
            }
        }


        vSmsButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val redPermission = checkSelfPermission(Manifest.permission.READ_SMS)
                if (redPermission == PackageManager.PERMISSION_GRANTED) {
                    // 有权限
                    getSmsInfo()
                } else {
                    // 没有权限
                    // 做个提示，用户点击了确定了之后再去请求调用权限
                    // 如果点击了不再提示，就不再获取了
                    // 如果不能使用则直接退出
                    requestPermissions(arrayOf(Manifest.permission.READ_SMS), PERMISSION_SMS_REQUEST_CODE)
                }
            } else {
                getSmsInfo()
            }
        }
    }

    /**
     * 获取短信信息
     * */
    private fun getSmsInfo () {
        val uri = Uri.parse("content://sms")
        val cursor = contentResolver.query(uri, null, null, null, null)
        val columnNames = cursor?.columnNames
        while (cursor?.moveToNext() == true) {
            println("===========================")
            columnNames?.forEach {
                val value = cursor.getString(cursor.getColumnIndex(it))
                println("Name: $it  <======>  Value: $value")
            }
            println("===========================")
        }
        cursor?.close()
    }

    /**
     * 获取通讯录信息
     * */
    private fun getContactsInfo () {
//        val uri = Uri.parse("content://${ContactsContract.AUTHORITY}/raw_contacts")
        val uri = ContactsContract.Contacts.CONTENT_URI
        val cursor = contentResolver.query(uri, arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
        ), null, null, null, null)
        val columnNames = cursor?.columnNames
        val userList = mutableListOf<UserInfo>()
        while (cursor?.moveToNext() == true) {
//            println("===========================")
//            columnNames?.forEach {
//                val value = cursor.getString(cursor.getColumnIndex(it))
//                println("Name: $it  <======>  Value: $value")
//            }
            userList.add(UserInfo(
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)),
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            ))
//            println("===========================")
        }
        cursor?.close()

        //
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        userList.forEach{
            val phoneCursor = contentResolver.query(phoneUri, arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID}=?", arrayOf(
                it.id
            ), null)
            if (phoneCursor?.moveToNext() == true) {
                it.phone = phoneCursor.getString(0)
            }
            phoneCursor?.close()
        }
        userList.forEach {
            println("user => $it")
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
        } else {
            insertCalendar()
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
        if (requestCode == PERMISSION_CONTACTS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 有权限
                getContactsInfo()
            } else {
                // 没权限
//                finish()
            }
        }
        if (requestCode == PERMISSION_SMS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 有权限
                getSmsInfo()
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

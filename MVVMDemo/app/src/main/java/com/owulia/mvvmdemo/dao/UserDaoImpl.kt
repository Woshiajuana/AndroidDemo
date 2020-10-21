package com.owulia.mvvmdemo.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.owulia.mvvmdemo.db.UserDatabaseHelper
import com.owulia.mvvmdemo.pojo.User
import com.owulia.mvvmdemo.utils.Constants

class UserDaoImpl (context: Context) : IUserDao {

    private val helper = UserDatabaseHelper(context)

    override fun addUser(user: User): Long {
        val db = helper.writableDatabase
        val values = user2Values(user)
        val result = db.insert(Constants.DB_TABLE_NAME, null, values)
        db.close()
        return result
    }

    override fun delUserById(id: Int): Int {
        val db = helper.writableDatabase
        val result = db.delete(Constants.DB_TABLE_NAME, Constants.FILED_ID, arrayOf(id.toString()))
        db.close()
        return result
    }

    override fun updateUser(user: User): Int {
        val db = helper.writableDatabase
        // 根据 ID 来更新内容
        val values = user2Values(user)
        val result = db.update(Constants.DB_TABLE_NAME, values, Constants.FILED_ID, arrayOf(user._id.toString()))
        db.close()
        return result
    }

    override fun getUserById(id: Int): User? {
        val db = helper.writableDatabase
        val sql = "select * from ${Constants.TABLE_NAME} where ${Constants.FILED_ID} = ?"
        val cursor = db.rawQuery(sql, arrayOf(id.toString()))
        var user: User? = null
        if (cursor.moveToNext()) {
            user = cursor2User(cursor)
        }
        cursor.close()
        db.close()
        return user
    }

    override fun listAllUser(): List<User> {
        val db = helper.writableDatabase
        val cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null, null)
        val userList = mutableListOf<User>()
        while (cursor.moveToNext()) {
            userList.add(cursor2User(cursor))
        }
        cursor.close()
        db.close()
        return userList
    }

    private fun cursor2User(
        cursor: Cursor
    ): User {
        return User(
            _id = cursor.getInt(cursor.getColumnIndex(Constants.FILED_ID)),
            userName = cursor.getString(cursor.getColumnIndex(Constants.FILED_USER_NAME)),
            password = cursor.getString(cursor.getColumnIndex(Constants.FILED_PASSWORD)),
            sex = cursor.getString(cursor.getColumnIndex(Constants.FILED_SEX)),
            age = cursor.getInt(cursor.getColumnIndex(Constants.FILED_AGE))
        )
    }

    private fun user2Values(user: User): ContentValues {
        return ContentValues().apply {
            put(Constants.FILED_USER_NAME, user.userName)
            put(Constants.FILED_PASSWORD, user.password)
            put(Constants.FILED_SEX, user.sex)
            put(Constants.FILED_AGE, user.age)
        }
    }
}
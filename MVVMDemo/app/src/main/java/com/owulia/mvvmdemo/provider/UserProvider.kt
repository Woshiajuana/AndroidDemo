package com.owulia.mvvmdemo.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.owulia.mvvmdemo.db.UserDatabaseHelper
import com.owulia.mvvmdemo.utils.Constants

class UserProvider : ContentProvider () {

    companion object {
        const val USER_MATCHER_CODE = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI("com.owulia.mvvmdemo", "user", USER_MATCHER_CODE)
        }
    }

    var userDatabaseHelper: UserDatabaseHelper? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val match = uriMatcher.match(uri)
        if (match != USER_MATCHER_CODE) {
            throw IllegalAccessException("参数错误")
        }
        val db = userDatabaseHelper?.writableDatabase
        val id = db?.insert(Constants.DB_TABLE_NAME, null, values)
        val resultUri = Uri.parse("content://com.owulia.mvvmdemo/user/${id}")
        // 插入数据成功，数据已经变化了，所以通知其他地方，谁监听就通知谁
        println("新增数据 => $id")
        context?.contentResolver?.notifyChange(resultUri, null)
        return resultUri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val match = uriMatcher.match(uri)
        if (match != USER_MATCHER_CODE) {
            throw IllegalAccessException("参数错误")
        }
        val db = userDatabaseHelper?.writableDatabase
        return db?.query(Constants.DB_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun onCreate(): Boolean {
        userDatabaseHelper = UserDatabaseHelper(context)
        return false
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 1
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 1
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}
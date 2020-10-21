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
            addURI("com.owulia.mvvmdemo", null, USER_MATCHER_CODE)
        }
    }

    var userDatabaseHelper: UserDatabaseHelper? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val rule = uriMatcher.match(uri)
        if (rule != USER_MATCHER_CODE) {
            throw IllegalAccessException("参数错误")
        }
        val db = userDatabaseHelper?.writableDatabase
        return db?.query(Constants.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
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
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
}
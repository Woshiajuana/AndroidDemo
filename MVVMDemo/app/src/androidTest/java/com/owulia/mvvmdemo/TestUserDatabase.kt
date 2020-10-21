package com.owulia.mvvmdemo

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.owulia.mvvmdemo.dao.UserDaoImpl
import com.owulia.mvvmdemo.pojo.User

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestUserDatabase {

    private lateinit var userDao: UserDaoImpl

    @Before
    fun testPrepare () {
        userDao = UserDaoImpl(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @Test
    fun testAddUser() {
        val user = User(
            userName = "lisi",
            password = "3456",
            sex = "female",
            age = 20
        )
        val result = userDao.addUser(user)
        println("testAddUser result => $result")
        assertNotEquals(-1, result)
    }

    @Test
    fun testListAllUser () {
        val users = userDao.listAllUser()
        println("users size is ${users.size}")
        users.forEachIndexed { index, user ->
            print("index => $index, user => $user")
        }
    }

}

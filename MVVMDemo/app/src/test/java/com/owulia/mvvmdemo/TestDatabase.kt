package com.owulia.mvvmdemo

import org.junit.Test
import androidx.test.platform.app.InstrumentationRegistry

class TestDatabase {

    @Test
    fun testCreate () {
        // 这里测试创建数据库
    }

    @Test
    fun testInsert () {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext // 测试插入数据
        val dao = Dao(appContext)
        dao.insert()
    }

    @Test
    fun testDelete () {
        // 测试删除
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext // 测试插入数据
        val dao = Dao(appContext)
        dao.delete()
    }

    @Test
    fun testUpdate () {
        // 测试修改
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext // 测试插入数据
        val dao = Dao(appContext)
        dao.update()
    }

    @Test
    fun testQuery () {
        // 测试查询
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext // 测试插入数据
        val dao = Dao(appContext)
        dao.query()
    }
}
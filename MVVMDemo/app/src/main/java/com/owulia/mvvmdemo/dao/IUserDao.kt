package com.owulia.mvvmdemo.dao

import com.owulia.mvvmdemo.pojo.User

interface IUserDao {

    /**
     * 添加用户
     * */
    fun addUser(user: User) : Long

    /**
     * 删除用户
     * */
    fun delUserById (id: Int) : Int

    /**
     * 更新用户
     * */
    fun updateUser (user: User) : Int

    /**
     * 查询用户
     * */
    fun getUserById (id: Int) : User

    /**
     * 查出所有的用户
     * */
    fun listAllUser () : List<User>
    
}
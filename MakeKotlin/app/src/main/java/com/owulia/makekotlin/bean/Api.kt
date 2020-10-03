package com.owulia.makekotlin.bean

import com.owulia.makekotlin.utils.Constants
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    /**
     * 注册账号检查服务
     * */
    @FormUrlEncoded
    @POST(Constants.DO_CHECK_ACCOUNT)
    fun doCheckAccount (@FieldMap params: Map<String, String>) : Call<RespBean<CheckAccountRespBean>>

    /**
     * 登录接口
     * */
    fun doUserLogin ()

    /**
     * 用户注册账号
     * */
    fun doUserRegister ()

    /**
     * 用户忘记密码
     * */
    fun doUserResetPassword ()

    /**
     * 发送短信
     * */
    fun doSendSms ()

    /**
     * 用户信息
     * */
    fun reqUserInfo ()

}
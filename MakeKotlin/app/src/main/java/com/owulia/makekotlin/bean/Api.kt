package com.owulia.makekotlin.bean

import com.owulia.makekotlin.utils.Constants
import retrofit2.Call
import retrofit2.http.*

interface Api {

    /**
     * 注册账号检查服务
     * */
    @FormUrlEncoded
    @POST(Constants.DO_CHECK_ACCOUNT)
    fun doCheckAccount (@FieldMap params: Map<String, String>) : Call<RespBean<RespCheckAccountBean>>

    /**
     * 登录接口
     * */
    @GET(Constants.DO_USER_LOGIN)
    fun doUserLogin (@QueryMap params: Map<String, String>)

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
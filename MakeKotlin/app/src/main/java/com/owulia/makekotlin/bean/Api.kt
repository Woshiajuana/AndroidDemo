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
    @JvmSuppressWildcards
    fun doCheckAccount (@FieldMap params: Map<String, Any?>) : Call<RespBean<RespCheckAccountBean>>

    /**
     * 登录接口
     * */
    @GET(Constants.DO_USER_LOGIN)
    @JvmSuppressWildcards
    fun doUserLogin (@QueryMap params: Map<String, Any?>)

    /**
     * 用户注册账号
     * */
    @FormUrlEncoded
    @POST(Constants.DO_USER_REGISTER)
    @JvmSuppressWildcards
    fun doUserRegister (@FieldMap params: Map<String, Any?>) : Call<RespBean<Any>>

    /**
     * 用户忘记密码
     * */
    @FormUrlEncoded
    @POST(Constants.DO_USER_RESET_PASSWORD)
    @JvmSuppressWildcards
    fun doUserResetPassword (@FieldMap params: Map<String, Any?>) : Call<RespBean<Any>>

    /**
     * 发送短信
     * */
    @FormUrlEncoded
    @POST(Constants.DO_SEND_SMS)
    @JvmSuppressWildcards
    fun doSendSms (@FieldMap params: Map<String, Any?>) : Call<RespBean<Any>>

    /**
     * 用户信息
     * */
    @FormUrlEncoded
    @POST(Constants.REQ_USER_INFO)
    @JvmSuppressWildcards
    fun reqUserInfo (@FieldMap params: Map<String, Any?>) : Call<RespBean<*>>

}
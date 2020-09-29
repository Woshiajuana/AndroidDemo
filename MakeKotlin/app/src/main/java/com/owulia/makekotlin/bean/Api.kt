package com.owulia.makekotlin.bean

import com.owulia.makekotlin.utils.Constants
import okhttp3.ResponseBody
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
    fun doCheckAccount (@FieldMap params: Map<String, String>) : Call<CheckAccountBean>

    /**
     * 注册账号检查服务
     * */
    @FormUrlEncoded
    @POST(Constants.DO_CHECK_ACCOUNT)
    fun doCheckAccount1 (@FieldMap params: Map<String, String>) : Call<ResponseBody>
}
package com.owulia.makekotlin.bean

import com.owulia.makekotlin.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    /**
     * 注册账号检查服务
     * */
    @POST(Constants.DO_CHECK_ACCOUNT)
    fun doCheckAccount (@Body params: Map<String, String>) : Call<CheckAccountBean>
}
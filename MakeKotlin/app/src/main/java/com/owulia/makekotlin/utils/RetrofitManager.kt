package com.owulia.makekotlin.utils

import com.owulia.makekotlin.bean.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager private constructor() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        val instant: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitManager() }
    }

    fun getApi () = retrofit.create(Api::class.java)

}
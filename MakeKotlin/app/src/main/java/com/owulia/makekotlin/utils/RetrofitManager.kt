package com.owulia.makekotlin.utils

import com.owulia.makekotlin.BuildConfig
import com.owulia.makekotlin.base.BaseApplication
import com.owulia.makekotlin.bean.Api
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {

    companion object {
        const val CACHE_NAME = "retrofitcache"
        val instant: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitManager() }
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
    }


    private val commonInterceptor = CommonInterceptor()

    // 设置
    private val cacheFile = File(BaseApplication.application!!.externalCacheDir, CACHE_NAME)

    // 生成缓存 50 M
    private val cache = Cache(cacheFile, 1024 * 1024 * 50)

    // 缓存拦截器
    private val cacheInterceptor = CacheInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(commonInterceptor)
        .addInterceptor(cacheInterceptor)
        .addInterceptor(loggingInterceptor)
        .cache(cache)
        .callTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApi () = retrofit.create(Api::class.java)

}
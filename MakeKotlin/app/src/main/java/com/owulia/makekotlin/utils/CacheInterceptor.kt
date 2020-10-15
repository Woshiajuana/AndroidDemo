package com.owulia.makekotlin.utils

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 缓存拦截器
 * */
class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        /**网络不可用用*/
        if (WowNetworkUtils.getInstance().isConnected()) {
            /**在请求头中加入：强制使用缓存，不访问网络*/
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        var response = chain.proceed(request)
        /**
         * 网络可用
         * */
        if (WowNetworkUtils.getInstance().isConnected()) {
            /**有网络时 在响应头中加入 设置缓存超时时间 0 个小时*/
            val maxAge = 0
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("pragma")
                .build()
        } else {
            /**无网络时 在响应头中加入 设置超时为4周*/
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("pragma")
                .build()
        }
        return response
    }

}
package com.owulia.makekotlin.utils

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CommonInterceptor : Interceptor {

    private val REQUEST_METHOD_GET = "GET"

    private val REQUEST_METHOD_POST = "POST"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = rebuildRequest(chain.request())
        val response = chain.proceed(request)
        return response
    }

    private fun rebuildRequest (request: Request) : Request {
        return when (request.method()) {
            REQUEST_METHOD_GET -> {
                rebuildGetRequest(request)
            }
            REQUEST_METHOD_POST -> {
                rebuildPostRequest(request)
            }
            else -> {
                request
            }
        }
    }

    private fun rebuildPostRequest (request: Request) : Request {
        /**
         * request.body() is FormBody 为true的条件为：
         * 在ApiService 中将POST请求中设置
         * 1，请求参数注解为@FieldMap
         * 2，方法注解为@FormUrlEncoded
         */
        val body = request.body()
        if (body is FormBody) {
            val builder = FormBody.Builder()
            /**
             * @FieldMap 注解 Map元素中 key 与 value 皆不能为null
             * 否则会出现NullPointerException
             * */
            for (i in 0 until body.size()) {
                body.value(i)?.let {
                    builder.add(body.name(i), it)
                }
            }
            builder.add("randomStr", WowCommonUtils.randomString(10))
                .add("reqDate", WowCommonUtils.formatDate(pattern = "yyyyMMdd"))
                .add("reqTime", WowCommonUtils.formatDate(pattern = "yyyyMMddHHmmss"))
                .add("tenantId", Constants.TENANT_ID)
        }
    }

    private fun rebuildGetRequest (request: Request) : Request {
        TODO("111")
    }

}

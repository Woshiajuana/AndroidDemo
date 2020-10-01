package com.owulia.makekotlin.utils

import cn.hutool.crypto.digest.DigestUtil
import com.google.gson.GsonBuilder
import okhttp3.*
import java.nio.charset.StandardCharsets
import java.util.*

class CommonInterceptor : Interceptor {

    private val REQUEST_METHOD_GET = "GET"

    private val REQUEST_METHOD_POST = "POST"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = rebuildRequest(chain.request())
        val response = chain.proceed(request)
        return response
    }

    private fun rebuildRequest(request: Request): Request {
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

    private fun rebuildPostRequest(request: Request): Request {
        /**
         * request.body() is FormBody 为true的条件为：
         * 在ApiService 中将POST请求中设置
         * 1，请求参数注解为@FieldMap
         * 2，方法注解为@FormUrlEncoded
         */
        val body = request.body()
        WowLogUtils.d(this, "${body is FormBody}")
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

            /**
             * 添加签名
             * */
            val params = HashMap<String, Any>()
            val newBody = builder.build()
            for (i in 0 until newBody.size()) {
                params[newBody.name(i)] = newBody.value(i)
            }
            val strSignTemp = signatureTempGenerate(params, Constants.ENCRYPT_KEY)
            val signature = DigestUtil.sha256Hex(strSignTemp.toByteArray(StandardCharsets.UTF_8))
            params[signature] = signature
            val mediaType = MediaType.parse("application/json;charset=utf-8")
            val requestBody = RequestBody.create(
                mediaType,
                GsonBuilder().disableHtmlEscaping().create().toJson(params)
            )
            return request.newBuilder()
                .addHeader("Authorization", "Basic c2hyZXc6c2hyZXc=")
                .addHeader("TENANT_ID", Constants.TENANT_ID)
                .post(requestBody).build()
        }
        return request.newBuilder().addHeader("Authorization", "Basic c2hyZXc6c2hyZXc=")
            .addHeader("TENANT_ID", Constants.TENANT_ID).build()
    }

    private fun rebuildGetRequest(request: Request): Request {
        return request
    }


    /**
     * 获取签名字符串
     * */
    private fun signatureTempGenerate(
        params: Map<String, Any>,
        key: String
    ): String {
        val signMap: SortedMap<String, Any> = TreeMap(params)
        val stringBuffer = StringBuffer()
        val es = signMap.entries
        for (e in es) {
            val entry = e as Map.Entry<*, *>
            val k = entry.key as String
            val v = entry.value
            /**
             * 空值不传递，不参与签名组串
             * */
            if (null != v && "" != v) {
                stringBuffer.append(k).append("=").append(v).append("&")
            }
        }
        /**
         * 排序后的字符串
         * */
        return stringBuffer.append("key=").append(key).toString()
    }

}

package com.owulia.makekotlin.utils

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
        TODO("111")
    }

    private fun rebuildGetRequest (request: Request) : Request {
        TODO("111")
    }

}

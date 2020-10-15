package com.owulia.makekotlin.base

import com.owulia.makekotlin.bean.RespBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

abstract class BaseCallback<T : Any> (
    private val view: IBaseView? = null
): Callback<RespBean<T>> {

    override fun onFailure(call: Call<RespBean<T>>, t: Throwable) {
        onComplete()
        onError(t)
    }

    override fun onResponse(call: Call<RespBean<T>>, response: Response<RespBean<T>>) {
        onComplete()
        val code = response.code()
        if (code == HttpURLConnection.HTTP_OK) {
            val body = response.body()
            if (body?.isOk() == true) {
                onSuccess(body.data)
            }
        } else {
            onError(response.message())
        }
    }

    abstract fun onSuccess (data: T?)

    open fun onError (t: Throwable) {
        onError("$t")
    }

    /**
     * 错误信息
     * */
    open fun onError (msg: String) {
        view?.toast(msg)
    }

    /**
     * 完成
     * */
    open fun onComplete () {
        view?.loadingDismiss()
    }
}
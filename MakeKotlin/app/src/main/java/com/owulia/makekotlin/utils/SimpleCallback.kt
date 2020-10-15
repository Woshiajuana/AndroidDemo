package com.owulia.makekotlin.utils

import com.google.gson.Gson
import com.owulia.makekotlin.base.IBaseView
import com.owulia.makekotlin.bean.RespBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.HttpURLConnection

abstract class SimpleCallback<T : Any> (
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
            if (body == null) {
                onError("网络繁忙，请稍后再试")
                return
            }
            if (body.isOk()) {
                onSuccess(body.data)
                return
            } else {
                 if (view?.callbackErrorCode(body.code?: -1) == true) {
                     onError(body.msg?: "")
                 }
            }
        } else {
            val string = response.errorBody()?.string()
            if (string != null) {
                try {
                    val body = Gson().fromJson(string, RespBean::class.java)
                    onError(body.msg?: "网络繁忙，请稍后再试[${code}]")
                    return
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
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
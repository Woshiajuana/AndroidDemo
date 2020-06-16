package com.owulia.taobaounion.presenter.impl

import com.owulia.taobaounion.model.Api
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.presenter.IHomePresenter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.RetrofitManager
import com.owulia.taobaounion.view.IHomeCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class HomePresenterImpl : IHomePresenter {

    // 加载分类数据
    override fun getCategories() {
        val api = RetrofitManager.instant.retrofit.create(Api::class.java)
        val task = api.getCategories()
        task.enqueue(object : Callback<Categories> {
            override fun onFailure(call: Call<Categories>, t: Throwable) {
                // 加载失败
                LogUtil.e(this, "请求错误 => $t")
            }

            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                // 数据结果
                val code = response.code()
                LogUtil.d(this, "result code is => $code")
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    val categories = response.body()
                    LogUtil.d(this, "result categories is => ${categories.toString()}")
                } else {
                    // 请求失败
                    LogUtil.i(this, "请求失败")
                }
            }
        })
    }

    override fun registerCallback(callback: IHomeCallback) {}

    override fun unregisterCallback(callback: IHomeCallback) {}
}
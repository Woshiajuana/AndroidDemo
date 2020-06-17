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

    private var mCallback: IHomeCallback? = null

    // 加载分类数据
    override fun getCategories() {
        mCallback?.onLoading()
        val api = RetrofitManager.instant.retrofit.create(Api::class.java)
        val task = api.getCategories()
        task.enqueue(object : Callback<Categories> {
            override fun onFailure(call: Call<Categories>, t: Throwable) {
                // 加载失败
                LogUtil.e(this, "请求错误 => $t")
                mCallback?.onNetworkError()
            }
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                // 数据结果
                val code = response.code()
                LogUtil.d(this, "result code is => $code")
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    val categories = response.body()
                    if (categories == null || categories.data.isEmpty()) {
                        mCallback?.onEmpty()
                    } else {
                        LogUtil.d(this, "result categories is => ${categories.toString()}")
                        mCallback?.onCategoriesLoaded(categories)
                    }
                } else {
                    // 请求失败
                    LogUtil.i(this, "请求失败")
                    mCallback?.onNetworkError()
                }
            }
        })
    }

    override fun registerCallback(callback: IHomeCallback) {
        mCallback = callback
    }

    override fun unregisterCallback(callback: IHomeCallback) {
        mCallback = null
    }
}
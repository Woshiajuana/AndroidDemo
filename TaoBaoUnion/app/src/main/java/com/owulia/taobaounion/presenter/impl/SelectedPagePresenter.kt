package com.owulia.taobaounion.presenter.impl

import com.owulia.taobaounion.model.Api
import com.owulia.taobaounion.model.domain.SelectedPageCategory
import com.owulia.taobaounion.model.domain.SelectedPageContent
import com.owulia.taobaounion.presenter.ISelectedPagePresenter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.RetrofitManager
import com.owulia.taobaounion.utils.UrlUtil
import com.owulia.taobaounion.view.ISelectedPageCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class SelectedPagePresenter : ISelectedPagePresenter {

    private var mCallback: ISelectedPageCallback? = null
    private var mCategory: SelectedPageCategory.Data? = null
    private val api =  RetrofitManager.instant.retrofit.create(Api::class.java)

    override fun getCategories() {
        mCallback?.onLoading()
        val task = api.getSelectedPageCategories()
        task.enqueue(object : Callback<SelectedPageCategory> {
            override fun onFailure(call: Call<SelectedPageCategory>, t: Throwable) {
                LogUtil.e(this, "请求错误 => $t")
                mCallback?.onNetworkError()
            }
            override fun onResponse(
                call: Call<SelectedPageCategory>,
                response: Response<SelectedPageCategory>
            ) {
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

    override fun getContentByCategory(item: SelectedPageCategory.Data) {
        mCategory = item
        api.getSelectedPageContent(UrlUtil.getSelectedPageContentUrl(mCategory!!.favorites_id))
            .enqueue(object : Callback<SelectedPageContent> {
                override fun onFailure(call: Call<SelectedPageContent>, t: Throwable) {
                    mCallback?.onNetworkError()
                }

                override fun onResponse(
                    call: Call<SelectedPageContent>,
                    response: Response<SelectedPageContent>
                ) {
                    // 数据结果
                    val code = response.code()
                    LogUtil.d(this, "result code is => $code")
                    if (code == HttpURLConnection.HTTP_OK) {
                        // 请求成功
                        val result = response.body()
                        if (result?.data == null) {
                            mCallback?.onEmpty()
                        } else {
                            LogUtil.d(this, "result categories is => ${result.toString()}")
                            mCallback?.onContentLoaded(result)
                        }
                    } else {
                        // 请求失败
                        LogUtil.i(this, "请求失败")
                        mCallback?.onNetworkError()
                    }
                }

            })

    }

    override fun reloadContent() {
        if (mCategory != null) {
            getContentByCategory(mCategory!!)
        }
    }

    override fun registerViewCallback(callback: ISelectedPageCallback) {
        mCallback = callback
    }

    override fun unregisterViewCallback(callback: ISelectedPageCallback) {
        mCallback = null
    }
}
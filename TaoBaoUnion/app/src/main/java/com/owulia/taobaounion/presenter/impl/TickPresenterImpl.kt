package com.owulia.taobaounion.presenter.impl

import com.owulia.taobaounion.model.Api
import com.owulia.taobaounion.model.domain.TicketParams
import com.owulia.taobaounion.model.domain.TicketResult
import com.owulia.taobaounion.presenter.ITickPresenter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.RetrofitManager
import com.owulia.taobaounion.utils.UrlUtil
import com.owulia.taobaounion.view.ITickCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Url
import java.lang.Error
import java.net.HttpURLConnection

class TickPresenterImpl : ITickPresenter {

    enum class LoadState {
        LOADING, SUCCESS, ERROR, EMPTY, NONE
    }

    private var currState = LoadState.NONE

    private var mCover: String? = null
    private var mResult: TicketResult? = null

    private var mViewCallback: ITickCallback? = null

    override fun getTicket(title: String, url: String, cover: String) {
        mViewCallback?.onLoading()
        currState = LoadState.LOADING
        mCover = UrlUtil.getCoverPath(cover)
        // 去获取淘宝口令
        val api = RetrofitManager.instant.retrofit.create(Api::class.java)
        val task = api.getTicket(TicketParams(UrlUtil.getCoverPath(url), title))
        task.enqueue(object : Callback<TicketResult>{
            override fun onFailure(call: Call<TicketResult>, t: Throwable) {
                // 加载失败
                LogUtil.e(this, "请求错误 => $t")
                currState = LoadState.ERROR
                mViewCallback?.onNetworkError()
            }

            override fun onResponse(call: Call<TicketResult>, response: Response<TicketResult>) {
                // 数据结果
                val code = response.code()
                LogUtil.d(this, "result code is => $code")
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    mResult = response.body()
                    if (mResult == null) {
                        currState = LoadState.EMPTY
                        mViewCallback?.onEmpty()
                    } else {
                        currState = LoadState.SUCCESS
                        LogUtil.d(this, "result data is => ${mResult.toString()}")
                        mViewCallback?.onTicketLoaded(mCover!!, mResult!!)
                    }
                } else {
                    // 请求失败
                    currState = LoadState.ERROR
                    LogUtil.i(this, "请求失败")
                    mViewCallback?.onNetworkError()
                }
            }
        })
    }

    override fun registerViewCallback(callback: ITickCallback) {
        if (currState != LoadState.NONE) {
            // 更新 UI
            if (currState == LoadState.SUCCESS) {
                mViewCallback?.onTicketLoaded(mCover!!, mResult!!)
            } else if (currState == LoadState.ERROR) {
                mViewCallback?.onNetworkError()
            } else if (currState == LoadState.EMPTY) {
                mViewCallback?.onEmpty()
            }
        }
        mViewCallback = callback
    }

    override fun unregisterViewCallback(callback: ITickCallback) {
        mViewCallback = null
    }

}
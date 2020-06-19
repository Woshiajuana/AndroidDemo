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
import java.net.HttpURLConnection

class TickPresenterImpl : ITickPresenter {

    override fun getTicket(title: String, url: String, cover: String) {
        // 去获取淘宝口令
        val api = RetrofitManager.instant.retrofit.create(Api::class.java)
        val task = api.getTicket(TicketParams(UrlUtil.getCoverPath(url), title))
        task.enqueue(object : Callback<TicketResult>{
            override fun onFailure(call: Call<TicketResult>, t: Throwable) {
                // 加载失败
                LogUtil.e(this, "请求错误 => $t")
            }

            override fun onResponse(call: Call<TicketResult>, response: Response<TicketResult>) {
                // 数据结果
                val code = response.code()
                LogUtil.d(this, "result code is => $code")
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    val categories = response.body()
//                    if (categories == null || categories.data.isEmpty()) {
//                    } else {
                        LogUtil.d(this, "result categories is => ${categories.toString()}")
//                    }
                } else {
                    // 请求失败
                    LogUtil.i(this, "请求失败")
                }
            }
        })
    }

    override fun registerViewCallback(callback: ITickCallback) {
//        TODO("Not yet implemented")
    }

    override fun unregisterViewCallback(callback: ITickCallback) {
//        TODO("Not yet implemented")
    }


}
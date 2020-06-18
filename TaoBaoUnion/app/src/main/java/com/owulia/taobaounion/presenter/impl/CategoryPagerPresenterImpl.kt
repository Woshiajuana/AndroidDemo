package com.owulia.taobaounion.presenter.impl

import com.owulia.taobaounion.model.Api
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.presenter.ICategoryPagerPresenter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.RetrofitManager
import com.owulia.taobaounion.utils.UrlUtil
import com.owulia.taobaounion.view.ICategoryPagerCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class CategoryPagerPresenterImpl private constructor() : ICategoryPagerPresenter {

    private val pagesInfo = HashMap<Int, Int>()

    companion object {
        const val DEFAULT_PAGE = 1
        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CategoryPagerPresenterImpl() }
    }

    override fun getContentByCategoryId(categoryId: Int) {
        callbacks.forEach {
            if (it.getCategoryId() == categoryId)
                it.onLoading()
        }
        LogUtil.d(this, "getContentByCategoryId 开始请求数据")
        val api = RetrofitManager.instant.retrofit.create(Api::class.java)
        // 加载内容
        var targetPage = pagesInfo[categoryId]
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE
            pagesInfo[categoryId] = targetPage
        }
        val url = UrlUtil.createHomePagerUrl(categoryId, targetPage)
        val task = api.getContentByCategoryId(url)
        LogUtil.d(this, "url => $url")
        task.enqueue(object : Callback<HomePagerContent> {
            override fun onFailure(call: Call<HomePagerContent>, t: Throwable) {
                // 加载失败
                LogUtil.e(this, "请求错误 => $t")
                handleNetworkError(categoryId)
            }

            override fun onResponse(
                call: Call<HomePagerContent>,
                response: Response<HomePagerContent>
            ) {
                // 数据结果
                val code = response.code()
                LogUtil.d(this, "result code is => $code")
                if (code == HttpURLConnection.HTTP_OK) {
                    val pageContent = response.body()
                    // 请求成功
                    LogUtil.i(this, "请求成功 $pageContent")
                    handleHomePagerContentResult(pageContent, categoryId)
                } else {
                    // 请求失败
                    LogUtil.i(this, "请求失败")
                    handleNetworkError(categoryId)
                }
            }
        })
    }

    private fun handleNetworkError (categoryId: Int) {
        callbacks.forEach {
            if (it.getCategoryId() == categoryId)
                it.onNetworkError()
        }
    }

    private fun handleHomePagerContentResult (pagerContent: HomePagerContent?, categoryId: Int) {
       val data = pagerContent?.data
        // 通知 UI 层通知数据
        callbacks.forEach {
            if (it.getCategoryId() == categoryId) {
                if (pagerContent == null || data == null || data.isEmpty()) {
                    it.onEmpty()
                } else {
                    it.onLooperListLoaded(data.subList(data.size - 5, data.size), categoryId)
                    it.onContentLoad(data, categoryId)
                }
            }
        }
    }

    override fun loaderMore(categoryId: Int) {
//        TODO("Not yet implemented")
    }

    override fun reload(categoryId: Int) {
//        TODO("Not yet implemented")
    }

    private val callbacks = ArrayList<ICategoryPagerCallback>()

    override fun registerViewCallback(callback: ICategoryPagerCallback) {
        if (!callbacks.contains(callback)) {
            callbacks.add(callback)
        }
    }

    override fun unregisterViewCallback(callback: ICategoryPagerCallback) {
        if (callbacks.contains(callback)) {
            callbacks.remove(callback)
        }
    }
}
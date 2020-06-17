package com.owulia.taobaounion.presenter.impl

import com.owulia.taobaounion.model.Api
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.presenter.ICategoryPagerPresenter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.RetrofitManager
import com.owulia.taobaounion.utils.UrlUtil
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
        // 加载内容
        val api = RetrofitManager.instant.retrofit.create(Api::class.java)
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
            }
            override fun onResponse(
                call: Call<HomePagerContent>,
                response: Response<HomePagerContent>
            ) {
                // 数据结果
                val code = response.code()
                LogUtil.d(this, "result code is => $code")
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    LogUtil.i(this, "请求成功${response.body()}")
                } else {
                    // 请求失败
                    LogUtil.i(this, "请求失败")
                }
            }
        })

        LogUtil.d(this, "url11 => $url")
    }

    override fun loaderMore(categoryId: Int) {
//        TODO("Not yet implemented")
    }

    override fun reload(categoryId: Int) {
//        TODO("Not yet implemented")
    }

    override fun registerViewCallback(callback: ICategoryPagerPresenter) {
//        TODO("Not yet implemented")
    }

    override fun unregisterViewCallback(callback: ICategoryPagerPresenter) {
//        TODO("Not yet implemented")
    }
}
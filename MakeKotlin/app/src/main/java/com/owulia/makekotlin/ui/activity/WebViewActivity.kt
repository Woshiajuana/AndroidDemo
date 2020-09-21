package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.model.WebViewOptionModel
import com.owulia.makekotlin.utils.Constants

class WebViewActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_web_view

    /**
     * 记录 url 是否有加载失败的情况
     * */
    private var isError = false

    /**
     * 加载的 url
     * */
    private var mUrl = ""

    override fun initView() {
        super.initView()

        intent?.apply {
            val webViewOptionModel = getParcelableExtra<WebViewOptionModel>(Constants.KEY_WEB_VIEW_OPTION_MODEL)
            webViewOptionModel?.apply {
                title?.let { vNavBar?.setTitle(it) }
                mUrl = link
            }
        }

        initWebView()

    }

    private fun initWebView () {

    }

    override fun initListener() {
        super.initListener()

    }

}

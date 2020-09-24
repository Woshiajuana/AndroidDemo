package com.owulia.makekotlin.ui.activity

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.webkit.*
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.bean.WebViewOptionBean
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.fragment_error.*

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

        render(RenderState.SUCCESS)

        intent?.apply {
            val webViewOptionModel =
                getParcelableExtra<WebViewOptionBean>(Constants.KEY_WEB_VIEW_OPTION_MODEL)
            webViewOptionModel?.apply {
                title?.let { vNavBar?.setTitle(it) }
                mUrl = link
            }
        }

        initWebView()

    }

    /**
     * 初始化 webview
     * */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        vWebView.apply {
            settings.apply {
                // 是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
                javaScriptEnabled = true
                // 设置js可以直接打开窗口，如window.open()，默认为false
                javaScriptCanOpenWindowsAutomatically = true
                // 是否可以缩放，默认true
                setSupportZoom(false)
                // 是否显示缩放按钮，默认false
                builtInZoomControls = false
                // 和setUseWideViewPort(true)一起解决网页自适应问题
                loadWithOverviewMode = true
                // 是否使用缓存
                setAppCacheEnabled(false)
                cacheMode = WebSettings.LOAD_NO_CACHE
                // 开启本地DOM存储
                domStorageEnabled = true
                // 加载图片
                loadsImagesAutomatically = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
            }
            webChromeClient = object : WebChromeClient() {
                // 网页加载进度
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    vProgressBar?.apply {
                        progress = newProgress
                        visibility = if (progress == 100) View.GONE else View.VISIBLE
                    }
                    super.onProgressChanged(view, newProgress)
                }

                // 获取到网页标题
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    var text = title ?: ""
                    if (title?.startsWith("http://") == true || title?.startsWith("https://") == true) {
                        text = ""
                    }
                    vNavBar?.setTitle(text)
                }
            }
            webViewClient = object : WebViewClient() {
                // 资源加载错误
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // 是否是为 main frame创建
                        if (request?.isForMainFrame == true) {
                            render(RenderState.ERROR)
                            isError = true
                        }
                    } else {
                        render(RenderState.ERROR)
                        isError = true
                    }
                }

                // 加载完成
                override fun onPageFinished(view: WebView?, url: String?) {
                    if (isError) {
                        vWebView?.visibility = View.GONE
                    } else {
                        vWebView?.visibility = View.VISIBLE
                    }
                    super.onPageFinished(view, url)
                }
            }
            loadUrl(mUrl)
        }
    }

    override fun initListener() {
        super.initListener()
        // 加载失败重新
        vRetryButton.setOnClickListener {
            render(RenderState.SUCCESS)
            isError = false
            vWebView?.reload()
        }
        vNavBar?.setOnLeftBtnClickListener
    }

    override fun onBackPressed() {
        if (vWebView?.canGoBack() == true) {
            vWebView?.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun release() {
        super.release()
        vWebView?.destroy()
    }

}

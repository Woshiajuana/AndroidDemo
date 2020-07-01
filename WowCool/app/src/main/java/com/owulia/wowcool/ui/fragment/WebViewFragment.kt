package com.owulia.wowcool.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.webkit.*
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.utils.ConstantsUtils
import com.owulia.wowcool.utils.WowLogUtils
import kotlinx.android.synthetic.main.fragment_web_view.*

/**
 * A simple [Fragment] subclass.
 */
class WebViewFragment : BaseFragment() {

    override fun getViewResourceId(): Int = R.layout.fragment_web_view

    // 记录url 是否有加载失败的情况
    private var isError = false

    override fun initView(view: View) {
        super.initView(view)
        arguments?.apply {
            getString(ConstantsUtils.WEB_VIEW_TITLE)?.let {
                vNavBar?.setTitle(it)
            }
            getString(ConstantsUtils.WEB_VIEW_URL)?.let {
                initWebView(it)
            }
        }
//        initWebView("https://ajuan.owulia.com")
    }

    override fun initEvent() {
        super.initEvent()
        vRefreshMark.setOnClickListener {
            it.visibility = View.GONE
            isError = false
            vWebView?.reload()
        }
    }

    // 初始化 webview
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView (url: String) {
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
                    var text = title?: ""
                    if (title?.startsWith("http://") == true || title?.startsWith("https://") == true) {
                        text = ""
                    }
                    // 安卓6.0以下根据标题去判断页面是否加载出错
//                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//                        if (title?.contains("404") == true
//                            || title?.contains("500") == true
//                            || title?.contains("Error") == true
//                            || title?.contains("找不到网页") == true
//                            || title?.contains("网页无法打开") == true) {
//                            isError = true
//                            vRefreshMark?.visibility = View.VISIBLE
//                        }
//                    }
                    vNavBar?.setTitle(text)
                }
            }
            webViewClient = object : WebViewClient() {
                // 加载错误 安卓6.0以上才有
                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    // 这个方法在 android 6.0才出现
                    // 这个方法在 android 6.0才出现
//                    val statusCode = errorResponse!!.statusCode
//                    if (404 == statusCode || 500 == statusCode || 502 == statusCode || 503 == statusCode) {
//                        isError = true
//                        vRefreshMark?.visibility = View.VISIBLE
//                    }
                }
                // 资源加载错误
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    if (request?.isForMainFrame == true) {//是否是为 main frame创建
//                        view.loadUrl("about:blank");// 避免出现默认的错误界面
//                        view.loadUrl(mErrorUrl);// 加载自定义错误页面

                    isError = true
                    vRefreshMark?.visibility = View.VISIBLE
                    }
//                    val url = request?.url
//                    WowLogUtils.d(this, "url => $url")
                }
                // 开始加载
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
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
            loadUrl(url)
        }
    }

    override fun onBackPressed(): Boolean {
        if (vWebView.canGoBack()) {
            vWebView.goBack()
            return true
        }
        return super.onBackPressed()
    }

    override fun release() {
        super.release()
        vWebView?.destroy()
    }
}

package com.owulia.wowcool.ui.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.webkit.*
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.ui.widget.WowWebViewDialog
import com.owulia.wowcool.utils.ConstantsUtils
import kotlinx.android.synthetic.main.fragment_web_view.*

/**
 * A simple [Fragment] subclass.
 */
class WebViewFragment : BaseFragment() {

    override val mNavBarRightImage: Int = R.drawable.ic_more_horiz

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
        initWebView("https://ajuan.owulia.com")
    }

    override fun initEvent() {
        super.initEvent()
        vRefreshMark.setOnClickListener {
            it.visibility = View.GONE
            isError = false
            vWebView?.reload()
        }
        vNavBar?.setOnRightBtnClickListener = {
            context?.let {
                WowWebViewDialog(it).show()
            }
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
                    // 是否是为 main frame创建
                    if (request?.isForMainFrame == true) {
                        isError = true
                        vRefreshMark?.visibility = View.VISIBLE
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

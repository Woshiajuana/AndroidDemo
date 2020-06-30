package com.owulia.wowcool.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.utils.WowLogUtils
import kotlinx.android.synthetic.main.fragment_web_view.*

/**
 * A simple [Fragment] subclass.
 */
class WebViewFragment : BaseFragment() {

    override fun getViewResourceId(): Int = R.layout.fragment_web_view

    override fun initView(view: View) {
        super.initView(view)
        initWebView()
    }

    // 初始化 webview
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView () {
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
                    vProgressBar.apply {
                        progress = newProgress
                        visibility = if (progress == 100) View.GONE else View.VISIBLE
                    }
                    super.onProgressChanged(view, newProgress)
                }
                // 获取到网页标题
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    var text = title?:""
                    if (title?.startsWith("http://") == true || title?.startsWith("https://") == true) {
                        text = ""
                    }
                    vNavBar?.setTitle(text)
                }
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
                ): Boolean {
                    WowLogUtils.d(this, "shouldOverrideUrlLoading => ${url}")
                    if (url?.startsWith("http://") == true || url?.startsWith("https://") == true) {
                        view?.loadUrl(url);
//                        vWebView.stopLoading();
                        return true
                    }
                    return false
                }
                // 开始加载
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    WowLogUtils.d(this, "onPageStarted => ${url}")
                    super.onPageStarted(view, url, favicon)
                }
                // 加载完成
                override fun onPageFinished(view: WebView?, url: String?) {
                    WowLogUtils.d(this, "onPageFinished => ${url}")
                    super.onPageFinished(view, url)
                }
            }
            loadUrl("http://www.baidu.com")
        }
    }

    override fun onBackPressed(): Boolean {
        WowLogUtils.d(this, "执行了返回呀")
        return super.onBackPressed()
    }

}

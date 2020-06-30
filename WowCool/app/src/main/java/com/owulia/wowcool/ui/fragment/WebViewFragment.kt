package com.owulia.wowcool.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.webkit.*
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

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView(view: View) {
        super.initView(view)
        vWebView.apply {
            settings.javaScriptEnabled = true
            webChromeClient = object : WebChromeClient() {
                // 网页加载进度
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    vProgressBar.apply {
                        progress = newProgress
                        visibility = if (progress == 100) View.GONE else View.VISIBLE
                    }
                    super.onProgressChanged(view, newProgress)
                    WowLogUtils.d(this, "onProgressChanged => ${newProgress}")
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    WowLogUtils.d(this, "onReceivedTitle => ${title}")
                }
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
                ): Boolean {
                    WowLogUtils.d(this, "shouldOverrideUrlLoading => ${url}")
                    return false
//                    return super.shouldOverrideUrlLoading(view, url)
                }
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    WowLogUtils.d(this, "shouldOverrideUrlLoading => ${request?.url}")
                    return false
//                    return super.shouldOverrideUrlLoading(view, request)
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

}

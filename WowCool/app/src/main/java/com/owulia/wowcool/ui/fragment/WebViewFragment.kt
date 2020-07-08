package com.owulia.wowcool.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.webkit.*
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.ui.widget.WowWebViewDialog
import com.owulia.wowcool.utils.AndroidBug5497Workaround
import com.owulia.wowcool.utils.ConstantsUtils
import com.owulia.wowcool.utils.WowClipDataUtils
import kotlinx.android.synthetic.main.fragment_web_view.*
import java.net.URL


/**
 * A simple [Fragment] subclass.
 */
class WebViewFragment : BaseFragment() {

    override val mNavBarRightImage: Int = R.drawable.ic_more_horiz

    override fun getViewResourceId(): Int = R.layout.fragment_web_view

    private var mWebViewDialog: WowWebViewDialog? = null

    // 记录url 是否有加载失败的情况
    private var isError = false

    override fun initView(view: View) {
        super.initView(view)
        AndroidBug5497Workaround.assistActivity(activity)
        arguments?.apply {
            getString(ConstantsUtils.WEB_VIEW_TITLE)?.let {
                vNavBar?.setTitle(it)
            }
            getString(ConstantsUtils.WEB_VIEW_URL)?.let {
                initWebView(it)
            }
        }
        context?.let {
            mWebViewDialog = WowWebViewDialog(it).apply {
                setExtendData(listOf(
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_wechat, "分享给好友", ConstantsUtils.KEY_WEB_VIEW_WECHAT),
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_friends, "分享到朋友圈", ConstantsUtils.KEY_WEB_VIEW_FRIENDS),
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_browser, "在浏览器打开", ConstantsUtils.KEY_WEB_VIEW_BROWSER),
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_collect, "收藏", ConstantsUtils.KEY_WEB_VIEW_COLLECT)
                ))
                setOperateData(listOf(
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_copy, "复制链接", ConstantsUtils.KEY_WEB_VIEW_COPY),
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_refresh, "刷新", ConstantsUtils.KEY_WEB_VIEW_REFRESH),
                    WowWebViewDialog.OperateItemBean(R.drawable.ic_web_view_search, "搜索页面内容", ConstantsUtils.KEY_WEB_VIEW_SEARCH)
                ))
                setOnItemClickListener = { item ->
                    dismiss()
                    when (item.key) {
                        ConstantsUtils.KEY_WEB_VIEW_WECHAT -> {

                        }
                        ConstantsUtils.KEY_WEB_VIEW_FRIENDS -> {

                        }
                        ConstantsUtils.KEY_WEB_VIEW_BROWSER -> {
                            val uri = Uri.parse(this@WebViewFragment.vWebView.url)
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(intent)
                        }
                        ConstantsUtils.KEY_WEB_VIEW_COLLECT -> {

                        }
                        ConstantsUtils.KEY_WEB_VIEW_COPY -> {
                            val uri = this@WebViewFragment.vWebView.url
                            WowClipDataUtils.copyText(context, uri)
                            utilToast("复制到剪切板")
                        }
                        ConstantsUtils.KEY_WEB_VIEW_REFRESH -> {
                            this@WebViewFragment.vWebView?.reload()
                        }
                        ConstantsUtils.KEY_WEB_VIEW_SEARCH -> {

                        }
                    }
                }
            }
        }
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
                var uri = ""
                try {
                    uri = URL(vWebView.url).host
                } catch (e: Exception) {
                }
                mWebViewDialog?.apply {
                    setTitle(String.format(getString(R.string.string_dialog_title, uri)))
                    show()
                }
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

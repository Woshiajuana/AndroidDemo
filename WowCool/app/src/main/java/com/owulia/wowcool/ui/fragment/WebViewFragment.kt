package com.owulia.wowcool.ui.fragment

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
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
            webViewClient = WebViewClient()
            loadUrl("https://www.baidu.com")
        }

    }

}

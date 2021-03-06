package com.owulia.wowcool.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.utils.ConstantsUtils
import com.owulia.wowcool.utils.WowLogUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    companion object {
        val instant: HomeFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HomeFragment() }
    }

    override val mNavBarTitle: Int = R.string.string_tab_bar_home
    override val mNavBarLeftImage: Int = ConstantsUtils.NAV_BAR_LEFT_IMAGE_NUM_NULL

    override fun getViewResourceId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        super.initView(view)
        setNavBarTitleLeftAlign()


        WowLogUtils.d(this, "你好呀")

//        runBlocking {
//            launch {
//
//            }
//        }

        GlobalScope.launch {

        }

    }

    override fun initEvent() {
        super.initEvent()
        vWebsiteMenu.setOnClickListener {
            handleJump(R.string.string_blog_text, ConstantsUtils.URL_DOCUMENT)
        }
        vAuthorMenu.setOnClickListener {
            handleJump(R.string.string_blog_text, ConstantsUtils.URL_BLOG)
        }
        vGitHubMenu.setOnClickListener {
            handleJump(R.string.string_github_text, ConstantsUtils.URL_GITHUB)
        }
        vHelpMenu.setOnClickListener {
            handleJump(R.string.string_help_text, ConstantsUtils.URL_HELP)
        }
    }

    private fun handleJump (title: Int, url: String) {
        val bundle = Bundle().apply {
            putString(ConstantsUtils.WEB_VIEW_TITLE, getString(title))
            putString(ConstantsUtils.WEB_VIEW_URL, url)
        }
        routerPush(R.id.action_mainFragment_to_webViewFragment, bundle)
    }

}

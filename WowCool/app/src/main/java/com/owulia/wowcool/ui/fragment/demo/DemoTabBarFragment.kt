package com.owulia.wowcool.ui.fragment.demo

import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class DemoTabBarFragment : BaseFragment() {

    override val mNavBarTitle: Int = R.string.string_demo_tabbar_text

    override fun getViewResourceId(): Int = R.layout.fragment_demo_tab_bar

}

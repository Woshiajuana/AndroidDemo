package com.owulia.wowcool.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    override val mNavBarTitle: String = "首页"
//    override val mNavBarLeftImage: Int = -1

    override fun getViewResourceId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        super.initView(view)
        setNavBarTitleLeftAlign()
    }

}

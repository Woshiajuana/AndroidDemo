package com.owulia.wowcool.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.utils.ConstantsUtils

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    override val mNavBarTitle: Int = R.string.string_tab_bar_home
    override val mNavBarLeftImage: Int = ConstantsUtils.NAV_BAR_LEFT_IMAGE_NUM_NULL

    override fun getViewResourceId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        super.initView(view)
        setNavBarTitleLeftAlign()
    }

}

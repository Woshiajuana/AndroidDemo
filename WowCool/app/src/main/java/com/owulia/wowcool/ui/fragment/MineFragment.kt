package com.owulia.wowcool.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.utils.ConstantsUtils

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment() {

    companion object {
        val instant: MineFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MineFragment() }
    }

    override val mNavBarTitle: Int = R.string.string_tab_bar_mine
    override val mNavBarLeftImage: Int = ConstantsUtils.NAV_BAR_LEFT_IMAGE_NUM_NULL

    override fun getViewResourceId(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        super.initView(view)
        setNavBarTitleLeftAlign()
    }

}

package com.owulia.makekotlin.ui.fragment

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment() {

    companion object {
        val instant: MineFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MineFragment() }
    }

    override val mNavBarLeftImg: Int = -1

    override val isStatusBarLightMode: Boolean = false

    override val mNavBarTitle: Int = R.string.string_tab_bar_mine

    override val mNavBarRightImg: Int = R.mipmap.ic_white_message

    override fun getContentViewResourceId(): Int = R.layout.fragment_mine

    override fun initView() {
        super.initView()
        vNavBar?.apply {
            setNavBarTitleLeftAlign()
            getVTitle().setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorMain))
        }
        vStatusBar?.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorMain))
        }
        render(RenderState.SUCCESS)
    }
}

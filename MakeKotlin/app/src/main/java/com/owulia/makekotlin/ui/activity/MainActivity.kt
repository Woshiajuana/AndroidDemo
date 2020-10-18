package com.owulia.makekotlin.ui.activity

import android.os.Handler
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.ui.fragment.HomeFragment
import com.owulia.makekotlin.ui.fragment.MaterialFragment
import com.owulia.makekotlin.ui.fragment.MineFragment
import com.owulia.makekotlin.utils.WowRouterManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val isUseNavBar: Boolean = false

    override val isUseStatusBarSeat: Boolean = false

    override fun getContentViewResourceId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        WowRouterManager.instant.redirect(this)
        render(RenderState.SUCCESS)
        vMainTabBar.apply {
            setItemIcon(resources.getDimension(R.dimen.dimen_tab_bar_icon).toInt())
            setItemText(
                ContextCompat.getColor(context, R.color.colorTabBarNormal),
                ContextCompat.getColor(context, R.color.colorTabBarActive)
            )
            addItem(
                R.mipmap.ic_home_normal,
                R.mipmap.ic_home_active,
                getString(R.string.string_tab_bar_home),
                HomeFragment.instant
            )
            addItem(
                R.mipmap.ic_yl_normal,
                R.mipmap.ic_yl_active,
                getString(R.string.string_tab_bar_yl),
                MaterialFragment.instant
            )
            addItem(
                R.mipmap.ic_mine_normal,
                R.mipmap.ic_mine_active,
                getString(R.string.string_tab_bar_mine),
                MineFragment.instant
            )
            setDivider(true, 1)
            build(supportFragmentManager)
            setOnPageChangeListener = { _, fragment ->
                fragment?.onStart()
            }
            switchItem(2)
        }
    }

}

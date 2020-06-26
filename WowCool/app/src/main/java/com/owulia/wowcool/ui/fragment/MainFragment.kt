package com.owulia.wowcool.ui.fragment

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment() {

    private val mMainFragmentViewModel by viewModels<MainFragmentViewModel>()

    override fun getViewResourceId(): Int = R.layout.fragment_main

    override fun initStatusBarSeat() {}

    override fun initView(view: View) {
        super.initView(view)
        wtMainTabBar.apply {
            setItemIcon(resources.getDimension(R.dimen.dimen_tab_bar_icon).toInt())
            setItemText(
                ContextCompat.getColor(context, R.color.colorTabBarNormal),
                ContextCompat.getColor(context, R.color.colorTabBarActive)
            )
            addItem(
                R.drawable.ic_tabbar_cool_normal,
                R.drawable.ic_tabbar_cool_active,
                getString(R.string.string_tab_bar_home),
                HomeFragment()
            )
            addItem(
                R.drawable.ic_tabbar_demo_normal,
                R.drawable.ic_tabbar_demo_active,
                getString(R.string.string_tab_bar_demo),
                DemoFragment.instant
            )
            addItem(
                R.drawable.ic_tabbar_mine_normal,
                R.drawable.ic_tabbar_mine_active,
                getString(R.string.string_tab_bar_mine),
                MineFragment()
            )
            setDivider(true, 1)
            setOnItemClickListener = { index, _ ->
                mMainFragmentViewModel.mNumCurrent.value = index
                true
            }
            build(childFragmentManager)
            switchItem(mMainFragmentViewModel.mNumCurrent.value?:0)
        }
    }



}

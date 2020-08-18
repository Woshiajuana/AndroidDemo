package com.owulia.makekotlin.ui.activity

import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val mNavBarTitle: Int = R.string.text_error_tip

    override fun getContentViewResourceId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
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
                R.mipmap.ic_home_normal,
                R.mipmap.ic_home_active,
                getString(R.string.string_tab_bar_yl),
                HomeFragment.instant
            )
            addItem(
                R.mipmap.ic_home_normal,
                R.mipmap.ic_home_active,
                getString(R.string.string_tab_bar_mine),
                HomeFragment.instant
            )
            setDivider(true, 1)
//            setOnItemClickListener = { index, _ ->
//                mMainFragmentViewModel.mNumCurrent.value = index
//                true
//            }
            build(supportFragmentManager)
            switchItem(0)
        }
    }

}

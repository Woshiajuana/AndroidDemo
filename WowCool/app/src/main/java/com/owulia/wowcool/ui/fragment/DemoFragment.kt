package com.owulia.wowcool.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.bean.DemoItemBean
import com.owulia.wowcool.ui.adapter.DemoAdapter
import com.owulia.wowcool.utils.ConstantsUtils
import com.owulia.wowcool.viewmodel.DemoFragmentViewModel
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * A simple [Fragment] subclass.
 */
class DemoFragment : BaseFragment() {

    companion object {
        val instant: DemoFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DemoFragment() }
    }

    override val mNavBarTitle: Int = R.string.string_tab_bar_demo
    override val mNavBarLeftImage: Int = ConstantsUtils.NAV_BAR_LEFT_IMAGE_NUM_NULL

    private val mDemoViewModel by viewModels<DemoFragmentViewModel>()

    override fun getViewResourceId(): Int = R.layout.fragment_demo

    override fun initView(view: View) {
        super.initView(view)
        setNavBarTitleLeftAlign()
        mDemoViewModel.arrDemo.value = mutableListOf (
            DemoItemBean(
                resources.getString(R.string.string_demo_tabbar_icon),
                resources.getString(R.string.string_demo_tabbar_text)),
            DemoItemBean(
                resources.getString(R.string.string_demo_iconfont_icon),
                resources.getString(R.string.string_demo_iconfont_text)),
            DemoItemBean(
                resources.getString(R.string.string_demo_wh_icon),
                resources.getString(R.string.string_demo_wh_text)
            ),
            DemoItemBean(
                resources.getString(R.string.string_demo_slide_menu_icon),
                resources.getString(R.string.string_demo_slide_menu_text)
            )
        )
        rvDemoMain.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = DemoAdapter(mDemoViewModel.arrDemo.value as MutableList<DemoItemBean>)
        }
    }

}

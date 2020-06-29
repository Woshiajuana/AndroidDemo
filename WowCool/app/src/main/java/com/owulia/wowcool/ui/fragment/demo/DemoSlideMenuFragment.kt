package com.owulia.wowcool.ui.fragment.demo

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.bean.DemoSlideMenuItem
import com.owulia.wowcool.ui.adapter.DemoSlideMenuAdapter
import com.owulia.wowcool.viewmodel.DemoSlideMenuFragmentViewModel
import kotlinx.android.synthetic.main.fragment_demo_slide_menu.*

/**
 * A simple [Fragment] subclass.
 */
class DemoSlideMenuFragment : BaseFragment() {

    override val mNavBarTitle: Int = R.string.string_demo_slide_menu_text

    private val mDemoSlideMenuViewModel by  viewModels<DemoSlideMenuFragmentViewModel>()
    private var mAdapter: DemoSlideMenuAdapter? = null

    override fun getViewResourceId(): Int = R.layout.fragment_demo_slide_menu

    override fun initView(view: View) {
        super.initView(view)
        vRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = DemoSlideMenuAdapter()
            adapter = mAdapter
        }
        mAdapter?.setData(mDemoSlideMenuViewModel.arrData.value!!)
    }

}

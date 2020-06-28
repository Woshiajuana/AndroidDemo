package com.owulia.wowcool.ui.fragment.demo

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.bean.DemoSlideMenuItem
import com.owulia.wowcool.ui.adapter.DemoSlideMenuAdapter
import kotlinx.android.synthetic.main.fragment_demo_slide_menu.*

/**
 * A simple [Fragment] subclass.
 */
class DemoSlideMenuFragment : BaseFragment() {

    override val mNavBarTitle: Int = R.string.string_demo_slide_menu_text

    private var mAdapter: DemoSlideMenuAdapter? = null

    override fun getViewResourceId(): Int = R.layout.fragment_demo_slide_menu

    override fun initView(view: View) {
        super.initView(view)
        vRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = DemoSlideMenuAdapter()
            adapter = mAdapter
        }
        val arrData = mutableListOf<DemoSlideMenuItem>(
            DemoSlideMenuItem("第一条数据"),
            DemoSlideMenuItem("第二条数据"),
            DemoSlideMenuItem("第三条数据"),
            DemoSlideMenuItem("第四条数据"),
            DemoSlideMenuItem("第五条数据"),
            DemoSlideMenuItem("第六条数据"),
            DemoSlideMenuItem("第七条数据"),
            DemoSlideMenuItem("第八条数据"),
            DemoSlideMenuItem("第九条数据"),
            DemoSlideMenuItem("第十条数据"),
            DemoSlideMenuItem("第十一条数据"),
            DemoSlideMenuItem("第十二条数据"),
            DemoSlideMenuItem("第十三条数据"),
            DemoSlideMenuItem("第十四条数据"),
            DemoSlideMenuItem("第十五条数据"),
            DemoSlideMenuItem("第十六条数据"),
            DemoSlideMenuItem("第十七条数据"),
            DemoSlideMenuItem("第十八条数据"),
            DemoSlideMenuItem("第十九条数据"),
            DemoSlideMenuItem("第二十条数据")
        )
        mAdapter?.setData(arrData)
    }

}

package com.owulia.wowcool.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.wowcool.R
import com.owulia.wowcool.adapter.DemoAdapter
import com.owulia.wowcool.bean.DemoItemBean
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * A simple [Fragment] subclass.
 */
class DemoFragment : Fragment() {


    private var arrDemoItem = mutableListOf<DemoItemBean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arrDemoItem = mutableListOf (
            DemoItemBean(resources.getString(R.string.string_demo_tabbar_icon), resources.getString(R.string.string_demo_tabbar_text)),
            DemoItemBean(resources.getString(R.string.string_demo_iconfont_icon), resources.getString(R.string.string_demo_iconfont_text)),
            DemoItemBean(resources.getString(R.string.string_demo_wh_icon), resources.getString(R.string.string_demo_wh_text)),
            DemoItemBean(resources.getString(R.string.string_demo_slide_menu_icon),
                resources.getString(R.string.string_demo_slide_menu_text))
        )
        rvDemoMain.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = DemoAdapter(arrDemoItem)
        }
    }

}

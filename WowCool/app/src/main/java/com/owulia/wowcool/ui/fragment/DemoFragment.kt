package com.owulia.wowcool.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.wowcool.R
import com.owulia.wowcool.ui.adapter.DemoAdapter
import com.owulia.wowcool.bean.DemoItemBean
import com.owulia.wowcool.viewmodel.DemoFragmentViewModel
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * A simple [Fragment] subclass.
 */
class DemoFragment : Fragment() {


    val TAG = "DemoFragment"


    companion object {
        val instant: DemoFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DemoFragment() }
    }


    private var arrDemoItem = mutableListOf<DemoItemBean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated")

        val demoViewModel by viewModels<DemoFragmentViewModel>()

        demoViewModel.arrDemo.value =  mutableListOf (
            DemoItemBean(resources.getString(R.string.string_demo_tabbar_icon), resources.getString(R.string.string_demo_tabbar_text)),
            DemoItemBean(resources.getString(R.string.string_demo_iconfont_icon), resources.getString(R.string.string_demo_iconfont_text)),
            DemoItemBean(resources.getString(R.string.string_demo_wh_icon), resources.getString(R.string.string_demo_wh_text)),
            DemoItemBean(
                resources.getString(R.string.string_demo_slide_menu_icon),
                resources.getString(R.string.string_demo_slide_menu_text)
            )
        )

        rvDemoMain.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = DemoAdapter(demoViewModel.arrDemo.value as MutableList<DemoItemBean>)
        }

    }

}

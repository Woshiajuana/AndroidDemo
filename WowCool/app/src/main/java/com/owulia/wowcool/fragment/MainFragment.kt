package com.owulia.wowcool.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.owulia.wowcool.R
import com.owulia.wowcool.adapter.DemoAdapter
import com.owulia.wowcool.bean.DemoItemBean
import com.owulia.wowcool.viewmodel.DemoViewModel
import kotlinx.android.synthetic.main.fragment_demo.*
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private var arrDemoItem = mutableListOf<DemoItemBean>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("XXXXX", "onCreateView")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        tvJump.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_demoSlideMenuFragment)
//        }
//
//        val demoViewModel by viewModels<DemoViewModel>()
//
//        demoViewModel.arrDemo.value =  mutableListOf (
//            DemoItemBean(resources.getString(R.string.string_demo_tabbar_icon), resources.getString(R.string.string_demo_tabbar_text)),
//            DemoItemBean(resources.getString(R.string.string_demo_iconfont_icon), resources.getString(R.string.string_demo_iconfont_text)),
//            DemoItemBean(resources.getString(R.string.string_demo_wh_icon), resources.getString(R.string.string_demo_wh_text)),
//            DemoItemBean(
//                resources.getString(R.string.string_demo_slide_menu_icon),
//                resources.getString(R.string.string_demo_slide_menu_text)
//            )
//        )
//
//        arrDemoItem  =  mutableListOf (
//            DemoItemBean(resources.getString(R.string.string_demo_tabbar_icon), resources.getString(R.string.string_demo_tabbar_text)),
//            DemoItemBean(resources.getString(R.string.string_demo_iconfont_icon), resources.getString(R.string.string_demo_iconfont_text)),
//            DemoItemBean(resources.getString(R.string.string_demo_wh_icon), resources.getString(R.string.string_demo_wh_text)),
//            DemoItemBean(
//                resources.getString(R.string.string_demo_slide_menu_icon),
//                resources.getString(R.string.string_demo_slide_menu_text)
//            )
//        )
//
//        rvDemoMain.apply {
//            layoutManager = GridLayoutManager(context, 3)
//            adapter = DemoAdapter(arrDemoItem)
//        }

        Log.d("XXXXX", "onActivityCreated")
        Log.d("XXXXX", "${requireActivity().supportFragmentManager}")
//
        wtMainTabBar.apply {
            setItemIcon(resources.getDimension(R.dimen.dimen_tab_bar_icon).toInt())
            setItemText(ContextCompat.getColor(context, R.color.colorTabBarNormal), ContextCompat.getColor(context, R.color.colorTabBarActive))
            addItem(R.mipmap.ic_tab_bar_home_normal, R.mipmap.ic_tab_bar_home_active,
                getString(R.string.string_tab_bar_home), HomeFragment()
            )
            addItem(R.mipmap.ic_tab_bar_demo_normal, R.mipmap.ic_tab_bar_demo_active,
                getString(R.string.string_tab_bar_demo), DemoFragment.instant
            )
            addItem(R.mipmap.ic_tab_bar_mine_normal, R.mipmap.ic_tab_bar_mine_active,
                getString(R.string.string_tab_bar_mine), MineFragment()
            )
            setDivider(true, 1)
            build(requireActivity().supportFragmentManager)
            switchItem(1)
        }

//        val supportFragmentManager = requireActivity().supportFragmentManager

//        supportFragmentManager.beginTransaction().add(R.id.flContent, DemoFragment()).commit()

    }

}

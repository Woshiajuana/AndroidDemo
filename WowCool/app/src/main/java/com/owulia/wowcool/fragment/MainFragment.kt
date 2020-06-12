package com.owulia.wowcool.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.owulia.wowcool.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        tvText.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_demoFragment)
        }

//        wtMainTabBar.apply {
//            setItemIcon(resources.getDimension(R.dimen.dimen_tab_bar_icon).toInt())
//            setItemText(ContextCompat.getColor(context, R.color.colorTabBarNormal), ContextCompat.getColor(context, R.color.colorTabBarActive))
//            addItem(R.mipmap.ic_tab_bar_home_normal, R.mipmap.ic_tab_bar_home_active,
//                getString(R.string.string_tab_bar_home), HomeFragment()
//            )
//            addItem(R.mipmap.ic_tab_bar_demo_normal, R.mipmap.ic_tab_bar_demo_active,
//                getString(R.string.string_tab_bar_demo), DemoFragment()
//            )
//            addItem(R.mipmap.ic_tab_bar_mine_normal, R.mipmap.ic_tab_bar_mine_active,
//                getString(R.string.string_tab_bar_mine), MineFragment()
//            )
//            setDivider(true, 1)
//            build(activity!!.supportFragmentManager)
//            switchItem(1)
//        }
    }

}

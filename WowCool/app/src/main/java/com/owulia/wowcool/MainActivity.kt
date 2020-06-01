package com.owulia.wowcool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.owulia.wowcool.fragment.HomeFragment
import com.owulia.wowcool.fragment.MaterialFragment
import com.owulia.wowcool.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        wtMainTabBar.apply {
//            setItemText(ContextCompat.getColor(context, R.color.colorTabBarNormal), ContextCompat.getColor(context, R.color.colorTabBarActive))
//            addedItem(R.mipmap.ic_tab_bar_home_normal, R.mipmap.ic_tab_bar_home_active, getString(R.string.string_tab_bar_home),
//                HomeFragment()
//            )
//            addedItem(R.mipmap.ic_tab_bar_demo_normal, R.mipmap.ic_tab_bar_demo_active, getString(R.string.string_tab_bar_demo),
//                MaterialFragment()
//            )
//            addedItem(R.mipmap.ic_tab_bar_mine_normal, R.mipmap.ic_tab_bar_mine_active, getString(R.string.string_tab_bar_mine),
//                MineFragment()
//            )
//            build(supportFragmentManager)
//            switchItem(0)
//        }

        wtMainTabBar.apply {
            setDivider(true, 100)
            build()
        }
    }
}

package com.owulia.wowcool

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.owulia.wowcool.fragment.HomeFragment
import com.owulia.wowcool.fragment.MaterialFragment
import com.owulia.wowcool.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vWowTabBar.apply {
            setItemText(getColor(R.color.colorTabBarNormal), getColor(R.color.colorTabBarActive))
            addedItem(R.mipmap.ic_tab_bar_home_normal, R.mipmap.ic_tab_bar_home_active, getString(R.string.string_tab_bar_home),
                HomeFragment()
            )
            addedItem(R.mipmap.ic_tab_bar_demo_normal, R.mipmap.ic_tab_bar_demo_active, getString(R.string.string_tab_bar_demo),
                MaterialFragment()
            )
            addedItem(R.mipmap.ic_tab_bar_mine_normal, R.mipmap.ic_tab_bar_mine_active, getString(R.string.string_tab_bar_mine),
                MineFragment()
            )
            build(supportFragmentManager)
            switchItem(0)
        }

    }
}

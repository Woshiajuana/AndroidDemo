package com.owulia.wowcool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.owulia.wowcool.fragment.HomeFragment
import com.owulia.wowcool.fragment.DemoFragment
import com.owulia.wowcool.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("WOW-COOL", "onCreate")



        var x = 1

        wtMainTabBar.apply {
            setItemIcon(resources.getDimension(R.dimen.dimen_tab_bar_icon).toInt())
            setItemText(ContextCompat.getColor(context, R.color.colorTabBarNormal), ContextCompat.getColor(context, R.color.colorTabBarActive))
            addItem(R.mipmap.ic_tab_bar_home_normal, R.mipmap.ic_tab_bar_home_active,
                getString(R.string.string_tab_bar_home), HomeFragment()
            )
            addItem(R.mipmap.ic_tab_bar_demo_normal, R.mipmap.ic_tab_bar_demo_active,
                getString(R.string.string_tab_bar_demo), DemoFragment()
            )
            addItem(R.mipmap.ic_tab_bar_mine_normal, R.mipmap.ic_tab_bar_mine_active,
                getString(R.string.string_tab_bar_mine), MineFragment()
            )
            setDivider(true, 1)
            build(supportFragmentManager)
        }
    }
}

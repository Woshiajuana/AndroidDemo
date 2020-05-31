package com.owulia.wowcool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vWowTabBar.apply {
            setItemText("#ff000000", "#ff007FD6")
            addedItem(R.mipmap.tab_bar_home_normal, R.mipmap.tab_bar_home_active, "首页",  HomeFragment())
            addedItem(R.mipmap.tab_bar_material_normal, R.mipmap.tab_bar_material_active, "有料",  HomeFragment())
            addedItem(R.mipmap.tab_bar_mine_normal, R.mipmap.tab_bar_mine_active, "我的",  HomeFragment())
            build(supportFragmentManager)
        }

    }
}

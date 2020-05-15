package com.owulia.makekotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.owulia.makekotlin.views.HomeFragment
import com.owulia.makekotlin.views.MaterialFragment
import com.owulia.makekotlin.views.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var homeFragment: Fragment
    private lateinit var materialFragment: Fragment
    private lateinit var mineFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //判断版本是否支持沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        // 初始化底部导航栏
        init()
    }

    private fun init () {
        homeFragment = HomeFragment()
        materialFragment = MaterialFragment()
        mineFragment = MineFragment()
        tabBarHome.setOnClickListener {
            handleSwitch(it, homeFragment)
        }
        tabBarMaterial.setOnClickListener {
            handleSwitch(it, materialFragment)
        }
        tabBarMine.setOnClickListener {
            handleSwitch(it, mineFragment)
        }
        handleSwitch(tabBarHome, homeFragment)
    }

    private fun handleSwitch (view: View, fragment: Fragment) {
        tabBarHomeImage.setImageResource(R.mipmap.tab_bar_home_normal)
        tabBarMaterialImage.setImageResource(R.mipmap.tab_bar_material_normal)
        tabBarMineImage.setImageResource(R.mipmap.tab_bar_mine_normal)
        val normalColor = ContextCompat.getColor(this,R.color.tabBarTextColorNormal)
        val activeColor = ContextCompat.getColor(this,R.color.tabBarTextColorActive)
        tabBarHomeText.setTextColor(normalColor)
        tabBarMaterialText.setTextColor(normalColor)
        tabBarMineText.setTextColor(normalColor)
        when(view.id) {
            R.id.tabBarHome -> {
                tabBarHomeImage.setImageResource(R.mipmap.tab_bar_home_active)
                tabBarHomeText.setTextColor(activeColor)
            }
            R.id.tabBarMaterial -> {
                tabBarMaterialImage.setImageResource(R.mipmap.tab_bar_material_active)
                tabBarMaterialText.setTextColor(activeColor)
            }
            R.id.tabBarMine -> {
                tabBarMineImage.setImageResource(R.mipmap.tab_bar_mine_active)
                tabBarMineText.setTextColor(activeColor)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}

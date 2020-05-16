package com.owulia.makekotlin

import android.graphics.Color
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


//        statusBar.layoutParams.height = getStatusBarHeight()

        //判断版本是否支持沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.statusBarColor = Color.TRANSPARENT
            //透明状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏

//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            headerView.setPadding(0, getStatusBarHeight(), 0, 0)
            Log.d(TAG, "headerView.height => ${headerView.layoutParams.height}")
            headerView.layoutParams.height = headerView.layoutParams.height + getStatusBarHeight()
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

    private fun getStatusBarHeight() : Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        Log.d(TAG,"result =>" + result.toString());
        return result
    }

}

package com.owulia.makekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.makekotlin.views.HomeFragment
import com.owulia.makekotlin.views.MaterialFragment
import com.owulia.makekotlin.views.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var numCurrent = 0

    private lateinit var homeFragment: Fragment
    private lateinit var materialFragment: Fragment
    private lateinit var mineFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, homeFragment).commit()
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
    }

    private fun handleSwitch (view: View, fragment: Fragment) {
        when(view.id) {
            R.id.tabBarHome -> {
                tabBarHome
            }
            R.id.tabBarMaterial -> {
            }
            R.id.tabBarMine -> {
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}

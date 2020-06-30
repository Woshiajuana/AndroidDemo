package com.owulia.wowcool.ui

import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseActivity
import com.owulia.wowcool.base.BaseFragment

class MainActivity : BaseActivity() {

    override fun getViewResourceId(): Int = R.layout.activity_main

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment)
        val fragmentManager = fragment?.childFragmentManager
        val currentFragment = fragmentManager?.fragments?.get(0)?.let { it as BaseFragment }
        when {
            // Fragment处理了返回操作返回true，不再执行其他代码
            currentFragment?.onBackPressed()?: false -> {}
            // Fragment不处理返回false，此时Activity里的其他代码发挥作用
            else -> super.onBackPressed()
        }
    }

}

package com.owulia.wowcool.ui

import android.os.Bundle
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseActivity
import com.owulia.wowcool.utils.WowLogUtils

class MainActivity : BaseActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        WowLogUtils.d(this, "MainActivity 执行了 ")

//        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.fragment))
//
//    }

    override fun getViewResourceId(): Int = R.layout.activity_main

    fun x () {
        baseContext
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp() || findNavController(R.id.fragment).navigateUp();
//    }

}

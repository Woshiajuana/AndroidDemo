package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import com.owulia.imoocmusicdemo.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView () {
        initNavBar(false,"码可音乐", true)
    }
}

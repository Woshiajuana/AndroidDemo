package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import com.owulia.imoocmusicdemo.R

class LoginActivity : BaseActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView () {
        initNavBar(false, "登录", false)
    }
}

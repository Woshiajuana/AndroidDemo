package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import android.view.View
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

    public fun onRegisterClick (view: View) {

    }

    public fun onCommitClick (view: View) {

    }

}

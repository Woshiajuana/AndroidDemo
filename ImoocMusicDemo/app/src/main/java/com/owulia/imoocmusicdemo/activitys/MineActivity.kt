package com.owulia.imoocmusicdemo.activitys

import android.content.Intent
import android.os.Bundle
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.utils.UserUtil

class MineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)

        initView()
    }

    private fun initView () {
        initNavBar(true, "个人中心", false)
    }

    fun onExit () {
        UserUtil.logout(this)
    }

    fun onChangePassword () {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }
}

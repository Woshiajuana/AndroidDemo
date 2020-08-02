package com.owulia.imoocmusicdemo.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.utils.UserUtil

class MineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine)

        initView()
    }

    private fun initView () {
        initNavBar(true, "个人中心1", false)
    }

    fun onExit (view: View) {
        setResult(2)
//        UserUtil.logout(this)
    }

    fun onChangePassword (view: View) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }
}

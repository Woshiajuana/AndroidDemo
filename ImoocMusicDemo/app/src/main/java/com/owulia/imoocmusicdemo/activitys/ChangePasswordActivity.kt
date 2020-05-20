package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import com.owulia.imoocmusicdemo.R

class ChangePasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    private fun initView () {
        initNavBar(true, "修改密码", false)
    }


}

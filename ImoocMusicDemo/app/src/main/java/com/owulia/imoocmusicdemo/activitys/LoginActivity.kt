package com.owulia.imoocmusicdemo.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.utils.UserUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView () {
        initNavBar(false, "登录", false)
    }

    fun onRegisterClick (view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun onCommitClick (view: View) {
        val phone = mInputPhone.getInputStr()
        val password = mInputPassword.getInputStr()
        if (!UserUtil.validateLogin(phone, password)) {
            return
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

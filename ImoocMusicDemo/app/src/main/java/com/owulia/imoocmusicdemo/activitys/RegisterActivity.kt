package com.owulia.imoocmusicdemo.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.utils.UserUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.mInputPassword
import kotlinx.android.synthetic.main.activity_login.mInputPhone
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView () {
        initNavBar(true, "注册", false)
    }

    fun onCommitClick (view: View) {
        val phone = mInputPhone.getInputStr()
        val password = mInputPassword.getInputStr()
        val passwordRed = mInputPasswordRed.getInputStr()
        if (!UserUtil.validateRegister(phone, password, passwordRed)) {
            return
        }
        ToastUtils.showShort("注册成功")
        onBackPressed()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}

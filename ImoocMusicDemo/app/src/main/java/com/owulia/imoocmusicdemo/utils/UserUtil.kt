package com.owulia.imoocmusicdemo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.activitys.LoginActivity

class UserUtil {
    companion object {
        fun validateLogin (phone: String, password: String) : Boolean {
            if (!RegexUtils.isMobileExact(phone)) {
                ToastUtils.showShort("无效手机号")
                return false
            }
            if (TextUtils.isEmpty(password)) {
                ToastUtils.showShort("请输入密码")
                return false
            }
            return true
        }

        fun validateRegister (phone: String, password: String, passwordRed: String) : Boolean {
            if (!RegexUtils.isMobileExact(phone)) {
                ToastUtils.showShort("无效手机号")
                return false
            }
            if (TextUtils.isEmpty(password)) {
                ToastUtils.showShort("请输入密码")
                return false
            }
            if (!password.equals(passwordRed)) {
                ToastUtils.showShort("两次密码不一致")
                return false
            }

            return true
        }

        fun logout (context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
            (context as Activity).overridePendingTransition(R.anim.open_enter, R.anim.open_exit)
        }
    }

}
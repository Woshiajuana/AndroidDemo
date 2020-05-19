package com.owulia.imoocmusicdemo.utils

import android.text.TextUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils

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
    }

}
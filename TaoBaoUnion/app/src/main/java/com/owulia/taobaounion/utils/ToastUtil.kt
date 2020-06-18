package com.owulia.taobaounion.utils

import android.annotation.SuppressLint
import android.widget.Toast
import com.owulia.taobaounion.base.BaseApplication

class ToastUtil {
    companion object {
        var toast: Toast? = null
        @SuppressLint("ShowToast")
        fun showToast (text: String) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.appContext, text, Toast.LENGTH_SHORT)
            } else {
                toast?.setText(text)
            }
            toast?.show()
        }
    }
}
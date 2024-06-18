package com.ohshell.headline.utils

import android.text.method.LinkMovementMethod
import android.widget.TextView

object OhShellTextUtil {
    /**
     * 设置富文本、超链接颜色
     * */
    fun setLinkColor(view: TextView, color: Int) {
        // 设置后才可以点击
        view.movementMethod = LinkMovementMethod.getInstance()

        // 设置链接颜色
        view.setLinkTextColor(color)
    }
}
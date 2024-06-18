package com.ohshell.headline.ui.activity

import android.os.Bundle
import com.ohshell.headline.R
import com.ohshell.headline.base.BaseLogicActivity
import com.ohshell.headline.utils.OhShellThemeUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class SplashActivity : BaseLogicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun initView() {
        super.initView()

        // 设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)
        if (OhShellThemeUtil.isDark(this)) {
            // 状态栏文字颜色
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        } else {
            // 状态栏文字颜色
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
    }

    override fun initData() {
        super.initData()
        showAgreementDialog()
    }

    private fun showAgreementDialog() {
        TODO("Not yet implemented")
    }
}
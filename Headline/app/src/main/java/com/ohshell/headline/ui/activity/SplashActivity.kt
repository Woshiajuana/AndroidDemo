package com.ohshell.headline.ui.activity

import com.ohshell.headline.base.BaseViewModelActivity
import com.ohshell.headline.databinding.ActivitySplashBinding
import com.ohshell.headline.ui.dialog.AgreementDialogFragment
import com.ohshell.headline.utils.OhShellLogUtil
import com.ohshell.headline.utils.OhShellThemeUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class SplashActivity : BaseViewModelActivity<ActivitySplashBinding>() {
    override fun initView() {
        super.initView()

        // 设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)
        if (OhShellThemeUtil.isDark(this)) {
            // 状态栏文字颜色
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        } else {
            //  状态栏文字颜色
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
    }

    override fun initData() {
        super.initData()
        showAgreementDialog()
//        binding
    }

    private fun showAgreementDialog() {
        AgreementDialogFragment.show(supportFragmentManager
        ) {
            OhShellLogUtil.d("我同意了协议")
//            binding.
        }
    }
}
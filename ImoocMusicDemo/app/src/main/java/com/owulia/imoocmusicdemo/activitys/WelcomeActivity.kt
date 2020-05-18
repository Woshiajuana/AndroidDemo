package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import com.owulia.imoocmusicdemo.R

// 延迟3秒
// 跳转页面
class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}

package com.owulia.base.ui.activity

import android.os.Bundle
import com.owulia.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instant.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instant.finishActivity(this)
    }

}
package com.owulia.imoocmusicdemo.activitys

import android.app.Activity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.nav_bar.*

open class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun initNavBar (isShowBack: Boolean, title: String, isShowMine: Boolean) {
        mBackBtn.visibility = if (isShowBack) View.VISIBLE else View.GONE
        mTitleTxt.text = title
        mMineBtn.visibility = if (isShowMine) View.VISIBLE else View.GONE

        mBackBtn.setOnClickListener {
            onBackPressed()
        }
    }


}
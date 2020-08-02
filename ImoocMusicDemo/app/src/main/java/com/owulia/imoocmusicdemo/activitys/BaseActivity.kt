package com.owulia.imoocmusicdemo.activitys

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.nav_bar.*

@SuppressLint("Registered")
open class BaseActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun initNavBar (isShowBack: Boolean, title: String, isShowMine: Boolean) {
        mBackBtn.visibility = if (isShowBack) View.VISIBLE else View.GONE
        mTitleTxt.text = title
        mMineBtn.visibility = if (isShowMine) View.VISIBLE else View.GONE

        mBackBtn.setOnClickListener {
            onBackPressed()
        }

        mMineBtn.setOnClickListener {
            val intent = Intent(this, MineActivity::class.java)
            startActivityForResult(intent, 1)
//            startActivity(intent)
        }
    }


}
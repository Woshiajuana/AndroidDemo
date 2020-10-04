package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.widget_user_header.view.*

class UserLoginActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_login

    private var mAccount = ""

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
        initParams()

        vUserHeader.apply {
            vHeaderTitle.text = mAccount
        }
    }

    /**
     * 参数获取
     * */
    private fun initParams () {
        intent.getStringExtra(Constants.KEY_ACCOUNT)?.let {
            mAccount = it
        }
    }

}

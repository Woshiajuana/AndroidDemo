package com.owulia.makekotlin.ui.activity

import com.bumptech.glide.Glide
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowCommonUtils
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.widget_user_header.view.*

class UserLoginActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_login

    private var mAccount = ""
    private var mAvatar = ""

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
        initParams()

        vUserHeader.apply {
            vHeaderTitle.text = WowCommonUtils.formatPhone(mAccount)
            Glide.with(this).load(mAvatar).into(vHeaderImg)
        }
    }

    /**
     * 参数获取
     * */
    private fun initParams () {
        intent.apply {
            getStringExtra(Constants.KEY_ACCOUNT)?.let {
                mAccount = it
            }
            getStringExtra(Constants.KEY_AVATAR)?.let {
                mAvatar = it
            }
        }
    }

}

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

    private var mAccount: String? = null
    private var mAvatar: String? = null

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
        initParams()

        vUserHeader.apply {
            mAccount?.let {
                vHeaderTitle.text = WowCommonUtils.formatPhone(it)
            }
            mAvatar?.let{
                Glide.with(this).load(it).into(vHeaderImg)
            }
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

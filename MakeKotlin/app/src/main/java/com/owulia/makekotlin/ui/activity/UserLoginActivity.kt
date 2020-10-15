package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.text.InputType
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseMvpActivity
import com.owulia.makekotlin.bean.RespUserInfoBean
import com.owulia.makekotlin.contacts.UserLoginContacts
import com.owulia.makekotlin.presenter.UserLoginPresenter
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowCommonUtils
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.widget_button.*
import kotlinx.android.synthetic.main.widget_user_header.view.*

class UserLoginActivity : BaseMvpActivity<UserLoginPresenter>(), UserLoginContacts.IView {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_login

    override fun bindPresenter(): UserLoginPresenter = UserLoginPresenter()

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

    override fun initListener() {
        super.initListener()

        /**登录*/
        vSubmitButton.setOnClickListener {
            mvpPresenter?.doUserLogin(mAccount, vInputPassword.text.toString())
        }

        /**
         * 切换显示
         * */
        vTypeSwitch.setOnClickListener {it as ImageView
            vInputPassword.apply {
                inputType = if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    /**隐藏*/
                    it.setImageResource(R.mipmap.ic_eye_off)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                } else {
                    /**显示*/
                    it.setImageResource(R.mipmap.ic_eye_on)
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                /**设置光标*/
                setSelection(length())
            }
        }

    }

    override fun callbackLoginSuccess(user: RespUserInfoBean) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

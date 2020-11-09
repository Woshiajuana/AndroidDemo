package com.owulia.user.ui.activity

import android.os.Bundle
import android.view.View
import com.owulia.base.ext.enable
import com.owulia.base.ext.onClick
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.user.R
import com.owulia.user.data.protocol.UserInfo
import com.owulia.user.injection.component.DaggerUserComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.presenter.LoginPresenter
import com.owulia.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

    }

    private fun initView () {

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)

        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPwdEt) { isBtnEnable() }

    }

    override fun onLoginResult(result: UserInfo) {
        toast("$result")
    }

    override fun injectComponent() {
        DaggerUserComponent
            .builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mLoginBtn -> {
            }
        }
    }

    fun isBtnEnable () : Boolean {
        return mMobileEt.text.isNullOrEmpty().not()
                && mPwdEt.text.isNullOrEmpty().not()
    }

}

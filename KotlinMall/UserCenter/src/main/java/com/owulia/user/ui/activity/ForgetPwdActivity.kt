package com.owulia.user.ui.activity

import android.os.Bundle
import android.view.View
import com.owulia.base.ext.enable
import com.owulia.base.ext.onClick
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.user.R
import com.owulia.user.injection.component.DaggerUserComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.presenter.ForgetPwdPresenter
import com.owulia.user.presenter.view.ForgetView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()

    }

    private fun initView () {

        mNextBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)

        mNextBtn.enable(mMobileEt) { isBtnEnable() }
        mNextBtn.enable(mVerifyCodeEt) { isBtnEnable() }
    }

    override fun onForgetResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
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
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送成功")
            }
            R.id.mNextBtn -> {
                mPresenter.forget(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    fun isBtnEnable () : Boolean {
        return mMobileEt.text.isNullOrEmpty().not()
                && mVerifyCodeEt.text.isNullOrEmpty().not()
    }

}

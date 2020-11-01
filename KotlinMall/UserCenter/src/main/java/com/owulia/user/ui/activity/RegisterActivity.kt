package com.owulia.user.ui.activity

import android.os.Bundle
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.user.R
import com.owulia.user.presenter.RegisterPresenter
import com.owulia.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()

//        mPresenter = RegisterPresenter()

        mRegisterBtn.setOnClickListener {
//            startActivity(intentFor<TestActivity>("id" to 5))
//            startActivity<TestActivity>("id" to 10)
            mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }
    }

    private fun initInjection () {
//        DaggerUserComponet
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }
}

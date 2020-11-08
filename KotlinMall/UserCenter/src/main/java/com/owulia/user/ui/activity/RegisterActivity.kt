package com.owulia.user.ui.activity

import android.os.Bundle
import com.owulia.base.common.AppManager
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.base.widgets.VerifyButton
import com.owulia.user.R
import com.owulia.user.injection.component.DaggerUserComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.presenter.RegisterPresenter
import com.owulia.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.setOnClickListener {
//            startActivity(intentFor<TestActivity>("id" to 5))
//            startActivity<TestActivity>("id" to 10)
            mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }

        mGetVerifyCodeBtn.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
            override fun onClick() {
                toast("获取验证码")
            }
        })
        mGetVerifyCodeBtn.setOnClickListener {
            mGetVerifyCodeBtn.requestSendVerifyNumber()
        }
    }

    override fun onRegisterResult(result: String) {
        toast(result)
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

    override fun onBackPressed() {
        super.onBackPressed()
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instant.exitApp(this)
        }
    }
}

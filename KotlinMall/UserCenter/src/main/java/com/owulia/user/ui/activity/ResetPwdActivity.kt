package com.owulia.user.ui.activity

import android.os.Bundle
import android.view.View
import com.owulia.base.ext.enable
import com.owulia.base.ext.onClick
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.user.R
import com.owulia.user.injection.component.DaggerUserComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.presenter.ResetPwdPresenter
import com.owulia.user.presenter.view.ResetView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()

    }

    private fun initView () {

        mConfirmBtn.onClick(this)

        mConfirmBtn.enable(mPwdConfirmEt) { isBtnEnable() }
        mConfirmBtn.enable(mPwdEt) { isBtnEnable() }

    }

    override fun onResetResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
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
            R.id.mConfirmBtn -> {
                mPresenter.reset(intent.getStringExtra("mobile")?: "", mPwdEt.text.toString())
            }
        }
    }

    fun isBtnEnable () : Boolean {
        return mPwdConfirmEt.text.isNullOrEmpty().not()
                && mPwdEt.text.isNullOrEmpty().not()
    }

}

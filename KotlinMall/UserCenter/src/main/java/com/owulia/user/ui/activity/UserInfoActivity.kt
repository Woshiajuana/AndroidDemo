package com.owulia.user.ui.activity

import android.os.Bundle
import android.view.View
import com.owulia.base.ui.activity.BaseMvpActivity
import com.owulia.user.R
import com.owulia.user.data.protocol.UserInfo
import com.owulia.user.injection.component.DaggerUserComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.presenter.UserInfoPresenter
import com.owulia.user.presenter.view.UserInfoView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()

    }

    private fun initView () {

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
                startActivity<ForgetPwdActivity>()
            }
        }
    }

}

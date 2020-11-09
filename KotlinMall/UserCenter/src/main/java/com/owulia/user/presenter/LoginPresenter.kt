package com.owulia.user.presenter

import com.owulia.base.ext.execute
import com.owulia.base.presenter.BasePresenter
import com.owulia.base.rx.BaseSubscriber
import com.owulia.user.data.protocol.UserInfo
import com.owulia.user.presenter.view.LoginView
import com.owulia.user.service.impl.UserServiceImpl
import javax.inject.Inject

class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun login (mobile: String, pwd: String, pushId: String) {

        if (!checkNetWork()) {
            return
        }

        // 业务逻辑
        mView.showLoading()
        userService.login(mobile, pwd, pushId)
            .execute(object : BaseSubscriber<UserInfo>(mView) {
                override fun onNext(t: UserInfo) {
                    mView.onLoginResult(t)
                }
            }, lifecycleProvider)
    }

}
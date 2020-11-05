package com.owulia.user.presenter

import com.owulia.base.ext.execute
import com.owulia.base.presenter.BasePresenter
import com.owulia.base.rx.BaseSubscriber
import com.owulia.user.presenter.view.RegisterView
import com.owulia.user.service.impl.UserServiceImpl
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun register (mobile: String, verifyCode: String, pwd: String) {
        // 业务逻辑
        userService.register(mobile, verifyCode, pwd)
            .execute(object : BaseSubscriber<Boolean>() {
                override fun onNext(t: Boolean) {
                    super.onNext(t)
                    mView.onRegisterResult(t)
                }
            }, lifecycleProvider)
    }

}
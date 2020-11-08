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

        if (!checkNetWork()) {
            return
        }

        // 业务逻辑
        mView.showLoading()
        userService.register(mobile, verifyCode, pwd)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    super.onNext(t)
                    if (t) {
                        mView.onRegisterResult("注册成功")
                    }
                }
            }, lifecycleProvider)
    }

}
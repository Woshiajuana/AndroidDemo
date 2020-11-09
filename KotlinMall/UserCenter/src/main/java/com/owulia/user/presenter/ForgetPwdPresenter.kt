package com.owulia.user.presenter

import com.owulia.base.ext.execute
import com.owulia.base.presenter.BasePresenter
import com.owulia.base.rx.BaseSubscriber
import com.owulia.user.presenter.view.ForgetView
import com.owulia.user.service.impl.UserServiceImpl
import javax.inject.Inject

class ForgetPwdPresenter @Inject constructor(): BasePresenter<ForgetView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun forget (mobile: String, verifyCode: String) {

        if (!checkNetWork()) {
            return
        }

        // 业务逻辑
        mView.showLoading()
        userService.forget(mobile, verifyCode)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    super.onNext(t)
                    if (t) {
                        mView.onForgetResult("验证成功")
                    }
                }
            }, lifecycleProvider)
    }

}
package com.owulia.user.presenter

import com.owulia.base.ext.execute
import com.owulia.base.presenter.BasePresenter
import com.owulia.base.rx.BaseSubscriber
import com.owulia.user.presenter.view.ResetView
import com.owulia.user.service.impl.UserServiceImpl
import javax.inject.Inject

class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    fun reset (mobile: String, verifyCode: String) {

        if (!checkNetWork()) {
            return
        }

        // 业务逻辑
        mView.showLoading()
        userService.reset(mobile, verifyCode)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    super.onNext(t)
                    if (t) {
                        mView.onResetResult("重置成功")
                    }
                }
            }, lifecycleProvider)
    }

}
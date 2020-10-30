package com.owulia.user.presenter

import com.owulia.base.presenter.BasePresenter
import com.owulia.user.presenter.view.RegisterView

class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register (mobile: String, verifyCode: String) {
        // 业务逻辑

        mView.onRegisterResult(true)
    }

}
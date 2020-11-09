package com.owulia.user.presenter.view

import com.owulia.base.presenter.view.BaseView
import com.owulia.user.data.protocol.UserInfo

interface UserInfoView : BaseView {

    fun onLoginResult (result: UserInfo)

}
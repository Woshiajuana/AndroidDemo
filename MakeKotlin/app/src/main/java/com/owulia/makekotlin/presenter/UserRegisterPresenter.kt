package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.model.UserRegisterModel

class UserRegisterPresenter : BasePresenter<UserRegisterContacts.IView>(), UserRegisterContacts.IPresenter {

    private val mvpModel = UserRegisterModel()

    override fun doSendSms(account: String) {
        /**
         * 校验手机号
         * */

    }

    override fun doUserRegister(account: String, smsCode: String) {
        /**
         * 校验手机号
         * */

    }

}
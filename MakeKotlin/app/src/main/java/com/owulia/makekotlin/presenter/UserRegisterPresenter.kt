package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.model.UserRegisterModel

class UserRegisterPresenter : BasePresenter<UserRegisterContacts.IView>(), UserRegisterContacts.IPresenter {

    private val mvpModel = UserRegisterModel()

    override fun doUserRegister(params: Map<String, Any>) {
        TODO("Not yet implemented")
    }

    override fun doSendSms(params: Map<String, Any>) {
        TODO("Not yet implemented")
    }

}
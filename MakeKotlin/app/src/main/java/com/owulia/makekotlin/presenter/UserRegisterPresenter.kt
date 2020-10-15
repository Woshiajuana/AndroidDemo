package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.model.UserRegisterModel
import com.owulia.makekotlin.utils.SimpleCallback
import com.owulia.makekotlin.utils.WowCommonUtils

class UserRegisterPresenter : BasePresenter<UserRegisterContacts.IView>(), UserRegisterContacts.IPresenter {

    private val mvpModel = UserRegisterModel()

    override fun doSendSms(account: String) {
        if (!WowCommonUtils.checkIsPhone(account)) {
            mvpView?.toast(R.string.string_error_phone_tip)
            return
        }
        mvpView?.loadingShow()
        mvpModel.doSendSms(account)
            .enqueue(object : SimpleCallback<Any>(mvpView) {
                override fun onSuccess(data: Any?) {
                    mvpView?.callbackSendSms()
                }
            })
    }

    override fun doUserRegister(account: String, password: String, smsCode: String) {
        if (!WowCommonUtils.checkIsPhone(account)) {
            mvpView?.toast(R.string.string_error_phone_tip)
            return
        }
        mvpModel.doUserRegister(account, password, smsCode)
            .enqueue(object : SimpleCallback<Any>(mvpView) {
                override fun onSuccess(data: Any?) {
                    mvpView?.callbackRegisterSuccess()
                }
            })
    }

}
package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.model.UserAccountModel

class UserAccountPresenter : BasePresenter<UserAccountContacts.IView>(), UserAccountContacts.IPresenter {

    private val mvpModel: UserAccountModel = UserAccountModel()

    override fun checkAccount(account: String) {

    }

    override fun getHistoryAccount() {
        val arrData = mvpModel.getHistoryAccount()
        mvpView?.callbackHistoryAccount(arrData)
    }

    override fun delHistoryAccount(position: Int) {
        mvpModel.delHistoryAccount(position)
    }

}
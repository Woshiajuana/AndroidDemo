package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.model.UserAccountModel

class UserAccountPresenter : BasePresenter(), UserAccountContacts.IPresenter {

    private val mvpModel: UserAccountModel = UserAccountModel()

    override fun checkAccount(account: String) {

    }

    override fun getHistoryAccount() {
        val arrData = mvpModel.getHistoryAccount()
    }

}
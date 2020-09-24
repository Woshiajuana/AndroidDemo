package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.contacts.UserAccountContacts

class UserAccountPresenter : BasePresenter (), UserAccountContacts.IPresenter {

    override fun checkAccount(account: String) {

    }

    override fun getHistoryAccount(): ArrayList<String> {
        TODO("Not yet implemented")
    }

}
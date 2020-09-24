package com.owulia.makekotlin.model

import com.owulia.makekotlin.contacts.UserAccountContacts

class UserAccountModel : UserAccountContacts.IModel {

    override fun checkAccount(account: String) {

    }

    override fun getHistoryAccount(): ArrayList<String> {
        TODO("Not yet implemented")
    }

}
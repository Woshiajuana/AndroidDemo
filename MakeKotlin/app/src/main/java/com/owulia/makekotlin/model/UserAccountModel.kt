package com.owulia.makekotlin.model

import com.owulia.makekotlin.contacts.UserAccountContacts

class UserAccountModel : UserAccountContacts.IModel {

    override fun checkAccount(account: String) {

    }

    override fun getHistoryAccount(): ArrayList<String> {
        return arrayListOf(
            "13111111111",
            "13111111112",
            "13111111113",
            "13111111114",
            "13111111115",
            "13111111116",
            "13111111117",
            "13111111118",
            "13111111119",
            "13111111110",
            "13111111121",
            "13111111122"
        )
    }

}
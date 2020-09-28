package com.owulia.makekotlin.model

import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.utils.RetrofitManager
import okhttp3.ResponseBody
import retrofit2.Call

class UserAccountModel : UserAccountContacts.IModel {

    private var mArrData = arrayListOf(
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

    override fun checkAccount(account: String) : Call<ResponseBody> {
        val params = HashMap<String, String>()
        params["account"] = account
        return RetrofitManager.instant.getApi().doCheckAccount1(params)
    }

    override fun getHistoryAccount(): ArrayList<String> {
        return mArrData
    }

    override fun delHistoryAccount(position: Int) {
        mArrData.removeAt(position)
    }

}
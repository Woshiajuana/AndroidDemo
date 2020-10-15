package com.owulia.makekotlin.model

import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.bean.RespUserInfoBean
import com.owulia.makekotlin.contacts.UserLoginContacts
import com.owulia.makekotlin.utils.RetrofitManager
import retrofit2.Call

class UserLoginModel : UserLoginContacts.IModel {

    override fun doUserLogin(account: String, password: String): Call<RespBean<RespUserInfoBean>> {
        val params = HashMap<String, Any>()
        params["account"] = account
        params["password"] = password
        return RetrofitManager.instant.getApi().doUserLogin(params)
    }

}
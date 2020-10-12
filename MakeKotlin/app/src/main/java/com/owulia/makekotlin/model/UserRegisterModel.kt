package com.owulia.makekotlin.model

import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.utils.RetrofitManager
import retrofit2.Call

class UserRegisterModel : UserRegisterContacts.IModel {

    override fun doUserRegister(account: String, smsCode: String): Call<RespBean<*>> {
        val params = HashMap<String, Any?>()
        params["loginNo"] = account
        params["smsType"] = "COMMON"
        return RetrofitManager.instant.getApi().doUserRegister(params)
    }

    override fun doSendSms(account: String): Call<RespBean<Any>> {
        val params = HashMap<String, Any?>()
        params["mobile"] = account
        params["smsType"] = "COMMON"
        return RetrofitManager.instant.getApi().doSendSms(params)
    }

}
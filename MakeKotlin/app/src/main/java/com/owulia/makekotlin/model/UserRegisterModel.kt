package com.owulia.makekotlin.model

import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.utils.RetrofitManager
import retrofit2.Call

class UserRegisterModel : UserRegisterContacts.IModel {

    override fun doUserRegister(params: Map<String, Any?>): Call<RespBean<*>> {
        return RetrofitManager.instant.getApi().doUserRegister(params)
    }

    override fun doSendSms(params: Map<String, Any?>): Call<RespBean<*>> {
        return RetrofitManager.instant.getApi().doSendSms(params)
    }

}
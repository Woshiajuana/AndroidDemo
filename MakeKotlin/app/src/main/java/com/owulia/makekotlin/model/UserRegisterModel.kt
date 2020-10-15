package com.owulia.makekotlin.model

import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.utils.RetrofitManager
import retrofit2.Call

class UserRegisterModel : UserRegisterContacts.IModel {

    override fun doUserRegister(account: String, password: String, smsCode: String): Call<RespBean<Any>> {
        val params = HashMap<String, Any?>()
        params["loginNo"] = account
        params["loginPassword"] = password
        params["smsCode"] = smsCode
        params["browser"] = "browser"
        params["dataSourceType"] = "NORMAL"
        params["imei"] = "imei"
        params["lbs"] = "12.21"
        params["loginChannel"] = "APP"
        params["loginIp"] = "127.0.0.1"
        params["loginLocation"] = "12.21"
        params["loginType"] = "PWD"
        params["oem"] = "oem"
        params["os"] = "ANDROID"
        return RetrofitManager.instant.getApi().doUserRegister(params)
    }

    override fun doSendSms(account: String): Call<RespBean<Any>> {
        val params = HashMap<String, Any?>()
        params["mobile"] = account
        params["smsType"] = "COMMON"
        return RetrofitManager.instant.getApi().doSendSms(params)
    }

}
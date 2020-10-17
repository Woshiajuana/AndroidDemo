package com.owulia.makekotlin.model

import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.bean.RespUserInfoBean
import com.owulia.makekotlin.contacts.UserLoginContacts
import com.owulia.makekotlin.utils.AesUtils
import com.owulia.makekotlin.utils.RetrofitManager
import retrofit2.Call

class UserLoginModel : UserLoginContacts.IModel {

    override fun doUserLogin(account: String, password: String): Call<RespBean<RespUserInfoBean>> {
        val params = HashMap<String, Any?>()
        params["username"] = account
        params["password"] = AesUtils.encrypt("shrewshrewshrews", password)
        params["code"] = "mf37"
        params["grant_type"] = "password"
        params["scope"] = "server"
        params["browser"] = "browser"
        params["imei"] = "862573030733839"
        params["lbs"] = "0.0,0.0"
        params["loginChannel"] = "APP"
        params["loginLocation"] = "0.0,0.0"
        params["loginType"] = "PWD"
        params["oem"] = "oem"
        params["loginIp"] = "127.0.0.1"
        params["dataSourceType"] = "NORMAL"
        params["userKind"] = "OPERATOR"
        params["os"] = "ANDROID"
        return RetrofitManager.instant.getApi().doUserLogin(params)
    }

}
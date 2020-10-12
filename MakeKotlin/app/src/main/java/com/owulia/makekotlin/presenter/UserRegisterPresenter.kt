package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.model.UserRegisterModel
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowCommonUtils
import com.owulia.makekotlin.utils.WowLogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class UserRegisterPresenter : BasePresenter<UserRegisterContacts.IView>(), UserRegisterContacts.IPresenter {

    private val mvpModel = UserRegisterModel()

    override fun doSendSms(account: String) {
        if (!WowCommonUtils.checkIsPhone(account)) {
            mvpView?.toast("手机号有误")
            return
        }
        mvpView?.loadingShow()
        mvpModel.doSendSms(account)
            .enqueue(object : Callback<RespBean<Any>> {
                override fun onFailure(call: Call<RespBean<Any>>, t: Throwable) {
                    WowLogUtils.d(this, "请求错误 => $t")
                    mvpView?.loadingDismiss()
                    mvpView?.toast(R.string.string_http_code_tip)
                }
                override fun onResponse(call: Call<RespBean<Any>>, response: Response<RespBean<Any>>) {
                    WowLogUtils.d(this, "请求成功 => ${response.body()}")
                    mvpView?.loadingDismiss()
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        val body = response.body()
                        if (body?.code == Constants.CODE_SUCCESS) {
                            mvpView?.callbackSendSms()
                        } else {
                            if (body?.msg?: "" == "") {
                                mvpView?.toast(R.string.string_http_code_tip)
                            } else {
                                mvpView?.toast(body?.msg?:"")
                            }
                        }
                    } else {
                        mvpView?.toast(response.message())
                        WowLogUtils.d(this, "请求失败 => ${response}")
                    }
                }
            })
    }

    override fun doUserRegister(account: String, smsCode: String) {
        if (!WowCommonUtils.checkIsPhone(account)) {
            mvpView?.toast("手机号有误")
            return
        }
        mvpModel.doUserRegister(account, smsCode)
    }

}
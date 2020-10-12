package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.model.UserRegisterModel
import com.owulia.makekotlin.utils.WowCommonUtils
import com.owulia.makekotlin.utils.WowLogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRegisterPresenter : BasePresenter<UserRegisterContacts.IView>(), UserRegisterContacts.IPresenter {

    private val mvpModel = UserRegisterModel()

    override fun doSendSms(account: String) {
        if (!WowCommonUtils.checkIsPhone(account)) {
            mvpView?.toast("手机号有误")
            return
        }
        mvpView?.loadingShow()
        mvpModel.doSendSms(account)
            .enqueue(object : Callback<RespBean<*>> {
                override fun onFailure(call: Call<RespBean<*>>, t: Throwable) {
                    WowLogUtils.d(this, "请求错误 => $t")
                    mvpView?.loadingDismiss()
                }
                override fun onResponse(call: Call<RespBean<*>>, response: Response<RespBean<*>>) {
                    WowLogUtils.d(this, "请求成功 => ${response.body()}")
                    mvpView?.loadingDismiss()
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
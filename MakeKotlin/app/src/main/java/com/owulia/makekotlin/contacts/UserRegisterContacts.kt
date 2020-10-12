package com.owulia.makekotlin.contacts

import com.owulia.makekotlin.base.IBasePresenter
import com.owulia.makekotlin.base.IBaseView
import com.owulia.makekotlin.bean.RespBean
import retrofit2.Call

class UserRegisterContacts {

    interface IView : IBaseView {

        /**
         * 注册成功
         * */
        fun callbackRegisterSuccess ()

        /**
         * 获取验证码成功
         * */
        fun callbackSendSms ()

    }

    interface IPresenter : IBasePresenter {

        /**
         * 注册
         * @param account [String] 账号
         * @param smsCode [String] 验证码
         * */
        fun doUserRegister (account: String, smsCode: String)

        /**
         * 获取验证码
         * @param account [String] 账号
         * */
        fun doSendSms (account: String)

    }

    interface IModel {

        /**
         * 注册
         * */
        fun doUserRegister (params: Map<String, Any?>) : Call<RespBean<*>>

        /**
         * 获取验证码
         * */
        fun doSendSms (params: Map<String, Any?>) : Call<RespBean<*>>

    }

}
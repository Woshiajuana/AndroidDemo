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
         * */
        fun doUserRegister (params: Map<String, Any?>)

        /**
         * 获取验证码
         * */
        fun doSendSms (params: Map<String, Any?>)

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
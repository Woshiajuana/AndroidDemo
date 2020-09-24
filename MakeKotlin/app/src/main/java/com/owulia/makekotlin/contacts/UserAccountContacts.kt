package com.owulia.makekotlin.contacts

import com.owulia.makekotlin.base.IBasePresenter
import com.owulia.makekotlin.base.IBaseView

class UserAccountContacts {

    interface IView : IBaseView {

        /**
         * 已注册跳转到登录页面
         * @param account [String] 账号
         * */
        fun callbackGoToLogin (account: String)

        /**
         * 未注册跳转到注册页面
         * @param account [String] 账号
         * */
        fun callbackGoToRegister (account: String)

    }

    interface IPresenter : IBasePresenter {

        /**
         * 检测账号是否注册
         * @param account [String] 账号
         * */
        fun checkAccount (account: String)

        /**
         * 获取历史账号数据
         * */
        fun getHistoryAccount () : ArrayList<String>

    }

    interface IModel {

        /**
         * 检测账号是否注册
         * @param account [String] 账号
         * */
        fun checkAccount(account: String)

        /**
         * 从缓存中获取历史账号数据
         * */
        fun getHistoryAccount () : ArrayList<String>

    }
}
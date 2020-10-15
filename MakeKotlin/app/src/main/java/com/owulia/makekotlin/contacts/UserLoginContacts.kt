package com.owulia.makekotlin.contacts

import com.owulia.makekotlin.base.IBasePresenter
import com.owulia.makekotlin.base.IBaseView
import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.bean.RespUserInfoBean
import retrofit2.Call

class UserLoginContacts {

    interface IView : IBaseView {

        /**
         * 登录成功
         * */
        fun callbackLoginSuccess (user: RespUserInfoBean)

    }

    interface IPresenter : IBasePresenter {

        /**
         * 登录
         * */
        fun doUserLogin (account: String, password: String)

    }

    interface IModel {

        /**
         * 登录
         * */
        fun doUserLogin (account: String, password: String) : Call<RespBean<RespUserInfoBean>>

    }

}
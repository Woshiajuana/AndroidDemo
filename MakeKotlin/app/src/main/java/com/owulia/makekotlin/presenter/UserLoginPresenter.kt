package com.owulia.makekotlin.presenter

import android.text.TextUtils
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.bean.RespUserInfoBean
import com.owulia.makekotlin.contacts.UserLoginContacts
import com.owulia.makekotlin.model.UserLoginModel
import com.owulia.makekotlin.utils.UserLoginCallback

class UserLoginPresenter : BasePresenter<UserLoginContacts.IView>(), UserLoginContacts.IPresenter {

    private val mvpModel: UserLoginModel = UserLoginModel()

    override fun doUserLogin(account: String?, password: String?) {
        if (TextUtils.isEmpty(account)) {
            mvpView?.toast(R.string.string_account_hint)
            return
        }
        if (TextUtils.isEmpty(password)) {
            mvpView?.toast(R.string.string_account_hint)
            return
        }
        mvpView?.loadingShow()
        mvpModel.doUserLogin(account!!, password!!)
            .enqueue(object : UserLoginCallback(mvpView) {
                override fun onSuccess(data: RespUserInfoBean) {
                    mvpView?.callbackLoginSuccess(data)
                }
            })
    }

}
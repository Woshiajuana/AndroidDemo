package com.owulia.makekotlin.presenter

import android.text.TextUtils
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.bean.RespCheckAccountBean
import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.model.UserAccountModel
import com.owulia.makekotlin.utils.SimpleCallback
import com.owulia.makekotlin.utils.WowCommonUtils

class UserAccountPresenter : BasePresenter<UserAccountContacts.IView>(),
    UserAccountContacts.IPresenter {

    private val mvpModel: UserAccountModel = UserAccountModel()

    override fun checkAccount(account: String) {
        if (TextUtils.isEmpty(account)) {
            mvpView?.toast(R.string.string_account_hint)
            return
        }
        if (!WowCommonUtils.checkIsPhone(account)) {
            mvpView?.toast(R.string.string_error_phone_tip)
            return
        }
        mvpView?.loadingShow()
        mvpModel.checkAccount(account)
            .enqueue(object : SimpleCallback<RespCheckAccountBean>(mvpView) {
                override fun onSuccess(data: RespCheckAccountBean?) {
                    if (data?.isRegister == "Y") {
                        mvpView?.callbackGoToLogin(account, data?.headPortrait)
                    } else {
                        mvpView?.callbackGoToRegister(account)
                    }
                }
            })
    }

    override fun getHistoryAccount() {
        val arrData = mvpModel.getHistoryAccount()
        mvpView?.callbackHistoryAccount(arrData)
    }

    override fun delHistoryAccount(position: Int) {
        mvpModel.delHistoryAccount(position)
    }

}
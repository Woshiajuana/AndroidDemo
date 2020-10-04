package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.bean.RespBean
import com.owulia.makekotlin.bean.RespCheckAccountBean
import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.model.UserAccountModel
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowLogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class UserAccountPresenter : BasePresenter<UserAccountContacts.IView>(), UserAccountContacts.IPresenter {

    private val mvpModel: UserAccountModel = UserAccountModel()

    override fun checkAccount(account: String) {
        mvpView?.loadingShow()
        mvpModel.checkAccount(account)
            .enqueue(object : Callback<RespBean<RespCheckAccountBean>> {
                override fun onFailure(call: Call<RespBean<RespCheckAccountBean>>, t: Throwable) {
                    WowLogUtils.d(this, "请求错误 => $t")
                    mvpView?.loadingDismiss()
                }
                override fun onResponse(
                    call: Call<RespBean<RespCheckAccountBean>>,
                    response: Response<RespBean<RespCheckAccountBean>>
                ) {
                    mvpView?.loadingDismiss()
                    val code = response.code()
                    WowLogUtils.d(this, "code => $code")
                    if (code == HttpURLConnection.HTTP_OK) {
                        WowLogUtils.d(this, "请求成功 => ${response.body()}")
                        val body = response.body()
                        if (body?.code == Constants.CODE_SUCCESS) {
                            if (body.data?.isRegister == "Y") {
                                mvpView?.callbackGoToLogin(account)
                            } else {
                                mvpView?.callbackGoToRegister(account, body.data?.headPortrait)
                            }
                        } else {
                            mvpView?.toast(R.string.string_http_code_tip)
                        }
                    } else {
                        mvpView?.toast(response.message()?: "请求失败，请稍后再试")
                        WowLogUtils.d(this, "请求失败 => ${response}")
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
package com.owulia.makekotlin.presenter

import com.owulia.makekotlin.base.BasePresenter
import com.owulia.makekotlin.bean.BaseRespBean
import com.owulia.makekotlin.bean.CheckAccountRespBean
import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.model.UserAccountModel
import com.owulia.makekotlin.utils.WowCommonUtils
import com.owulia.makekotlin.utils.WowLogUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class UserAccountPresenter : BasePresenter<UserAccountContacts.IView>(), UserAccountContacts.IPresenter {

    private val mvpModel: UserAccountModel = UserAccountModel()

    override fun checkAccount(account: String) {
        mvpView?.loadingShow()
//        mvpView?.toast(WowCommonUtils.formatDate())
//        mvpView?.toast(WowCommonUtils.randomString())
        mvpModel.checkAccount(account)
            .enqueue(object : Callback<BaseRespBean<CheckAccountRespBean>> {
                override fun onFailure(call: Call<BaseRespBean<CheckAccountRespBean>>, t: Throwable) {
                    WowLogUtils.d(this, "请求错误 => $t")
                    mvpView?.loadingDismiss()
                }
                override fun onResponse(
                    call: Call<BaseRespBean<CheckAccountRespBean>>,
                    response: Response<BaseRespBean<CheckAccountRespBean>>
                ) {
                    mvpView?.loadingDismiss()
                    val code = response.code()
                    WowLogUtils.d(this, "code => $code")
                    if (code == HttpURLConnection.HTTP_OK) {
                        WowLogUtils.d(this, "请求成功 => ${response.body()}")
                    } else {
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
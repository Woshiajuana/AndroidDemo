package com.owulia.user.data.repository

import com.owulia.base.data.net.RetrofitFactory
import com.owulia.base.data.protocol.BaseResp
import com.owulia.user.data.api.UserApi
import com.owulia.user.data.protocol.LoginReq
import com.owulia.user.data.protocol.RegisterReq
import com.owulia.user.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instant.create(UserApi::class.java)
            .register(RegisterReq(mobile, pwd, verifyCode))
    }

    fun login (mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instant.create(UserApi::class.java)
            .login(LoginReq(mobile, pwd, pushId))
    }

}
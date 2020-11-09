package com.owulia.user.data.api

import com.owulia.base.data.protocol.BaseResp
import com.owulia.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {

    @POST("userCenter/register")
    fun register (@Body req: RegisterReq) : Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login (@Body req: LoginReq) : Observable<BaseResp<UserInfo>>

    @POST("userCenter/register")
    fun forget (@Body req: ForgetReq) : Observable<BaseResp<String>>

    @POST("userCenter/register")
    fun reset (@Body req: ResetReq) : Observable<BaseResp<String>>

}
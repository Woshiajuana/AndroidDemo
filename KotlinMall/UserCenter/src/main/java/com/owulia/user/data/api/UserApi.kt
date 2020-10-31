package com.owulia.user.data.api

import com.owulia.base.data.protocol.BaseResp
import com.owulia.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {

    @POST("userCenter/register")
    fun register (@Body req: RegisterReq) : Observable<BaseResp<String>>

}
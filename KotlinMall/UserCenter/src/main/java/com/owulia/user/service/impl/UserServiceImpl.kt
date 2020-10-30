package com.owulia.user.service.impl

import com.owulia.user.service.UserService
import rx.Observable

class UserServiceImpl : UserService {

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }

}
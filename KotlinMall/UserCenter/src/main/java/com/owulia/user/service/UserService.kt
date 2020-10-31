package com.owulia.user.service

import rx.Observable

interface UserService {

    fun register (mobile: String, pwd: String, verifyCode: String) : Observable<Boolean>

}
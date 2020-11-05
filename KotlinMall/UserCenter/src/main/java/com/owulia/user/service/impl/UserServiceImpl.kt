package com.owulia.user.service.impl

import com.owulia.base.ext.convertBoolean
import com.owulia.user.data.repository.UserRepository
import com.owulia.user.service.UserService
import rx.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return repository.register(mobile, pwd, verifyCode)
            .convertBoolean()

    }

}
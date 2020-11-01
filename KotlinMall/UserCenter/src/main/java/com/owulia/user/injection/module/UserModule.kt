package com.owulia.user.injection.module

import com.owulia.user.service.UserService
import com.owulia.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl) : UserService {
        return service
    }

}
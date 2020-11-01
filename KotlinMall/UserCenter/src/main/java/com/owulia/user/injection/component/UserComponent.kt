package com.owulia.user.injection.component

import com.owulia.user.injection.module.UserModule
import com.owulia.user.ui.activity.RegisterActivity
import dagger.Component

@Component(modules = [UserModule::class])
interface UserComponent {
    fun inject(activity: RegisterActivity)
}
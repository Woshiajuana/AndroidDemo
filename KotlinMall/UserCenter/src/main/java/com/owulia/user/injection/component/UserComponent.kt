package com.owulia.user.injection.component

import com.owulia.base.injection.PerComponentScope
import com.owulia.base.injection.component.ActivityComponent
import com.owulia.user.injection.module.UserModule
import com.owulia.user.ui.activity.ForgetPwdActivity
import com.owulia.user.ui.activity.LoginActivity
import com.owulia.user.ui.activity.RegisterActivity
import com.owulia.user.ui.activity.ResetPwdActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class])
interface UserComponent {
    fun inject(activity: RegisterActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: ForgetPwdActivity)

    fun inject(activity: ResetPwdActivity)
}
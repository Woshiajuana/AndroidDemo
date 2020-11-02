package com.owulia.base.injection.component

import android.content.Context
import com.owulia.base.injection.ActivityScope
import com.owulia.base.injection.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun context () : Context
}
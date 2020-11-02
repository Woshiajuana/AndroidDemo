package com.owulia.base.injection.component

import android.content.Context
import com.owulia.base.injection.module.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun context () : Context
}
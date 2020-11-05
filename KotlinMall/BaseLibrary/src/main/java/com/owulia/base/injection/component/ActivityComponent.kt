package com.owulia.base.injection.component

import android.app.Activity
import android.content.Context
import com.owulia.base.injection.ActivityScope
import com.owulia.base.injection.module.ActivityModule
import com.owulia.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, LifecycleProviderModule::class])
interface ActivityComponent {
    fun activity () : Activity
    fun context() : Context
    fun lifecycleProvider () : LifecycleProvider<*>
}
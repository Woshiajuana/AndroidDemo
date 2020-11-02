package com.owulia.base.injection.module

import android.content.Context
import com.owulia.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providesContext() : Context {
        return context
    }

}
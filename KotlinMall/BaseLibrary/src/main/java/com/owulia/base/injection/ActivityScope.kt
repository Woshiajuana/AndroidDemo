package com.owulia.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope


@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

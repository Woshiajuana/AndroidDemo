package com.owulia.base.ext

import com.owulia.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,
lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
        .compose(lifecycleProvider.bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber)
}
package com.owulia.base.ext

import android.view.View
import com.owulia.base.data.protocol.BaseResp
import com.owulia.base.rx.BaseFunc
import com.owulia.base.rx.BaseFuncBoolean
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

fun <T> Observable<BaseResp<T>>.convert() : Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T>  Observable<BaseResp<T>>.convertBoolean() : Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}
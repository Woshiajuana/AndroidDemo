package com.owulia.base.rx

import com.owulia.base.presenter.view.BaseView
import rx.Subscriber

open class BaseSubscriber<T> (val view: BaseView) : Subscriber<T>() {
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        view.hideLoading()
    }

    override fun onError(e: Throwable?) {
        view.hideLoading()
    }
}
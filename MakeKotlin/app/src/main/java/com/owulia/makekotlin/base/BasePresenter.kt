package com.owulia.makekotlin.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

open class BasePresenter<V : IBaseView> : IBasePresenter  {

    private var mvpReference: Reference<V>? = null

    val mvpView: V? by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { mvpReference?.get()  }

    /**
     * 挂载 View
     * */
    override fun attachView(view: IBaseView) {
        mvpReference = WeakReference(view as V)
    }

    /**
     * 卸载 View
     * */
    override fun detachView() {
        mvpReference?.clear()
        mvpReference = null
    }

}
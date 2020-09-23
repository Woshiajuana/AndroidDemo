package com.owulia.makekotlin.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

class BasePresenter <V : IBaseView> : IBasePresenter  {

    var mvpRef: Reference<V>? = null

    /**
     * 挂载 View
     * */
    fun attachView(view: V) {
        mvpRef = WeakReference(view)
    }

    /**
     * 判断 IBaseView 的生命周期是否结束
     * */
    fun isViewAttach () : Boolean {
        return mvpRef != null && mvpRef?.get() != null
    }

    /**
     * 卸载 View
     * */
    override fun detachView() {
        mvpRef?.clear()
        mvpRef = null
    }

}
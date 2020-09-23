package com.owulia.makekotlin.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

class BasePresenter : IBasePresenter  {

    var mvpRef: Reference<IBaseView>? = null

    /**
     * 挂载 View
     * */
    override fun attachView(view: IBaseView) {
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
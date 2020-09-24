package com.owulia.makekotlin.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

open class BasePresenter : IBasePresenter  {

    var mvpReference: Reference<IBaseView>? = null

    var mvpView = {
        if (isViewAttach()) {
            mvpReference?.get()
        } else {
            null
        }
    }

    /**
     * 挂载 View
     * */
    override fun attachView(view: IBaseView) {
        mvpReference = WeakReference(view)
    }

    /**
     * 判断 IBaseView 的生命周期是否结束
     * */
    fun isViewAttach () : Boolean {
        return mvpReference != null && mvpReference?.get() != null
    }

    /**
     * 卸载 View
     * */
    override fun detachView() {
        mvpReference?.clear()
        mvpReference = null
    }

}
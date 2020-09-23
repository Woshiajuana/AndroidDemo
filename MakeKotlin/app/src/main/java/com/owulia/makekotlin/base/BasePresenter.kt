package com.owulia.makekotlin.base

import java.lang.ref.Reference

class BasePresenter <V : IBaseView> : IBasePresenter<V>  {

    var mvpRef: Reference<V>? = null

    /**
     *
     * */
    override fun attachView(view: V) {

    }

    /**
     *
     * */
    override fun detachView() {

    }

}
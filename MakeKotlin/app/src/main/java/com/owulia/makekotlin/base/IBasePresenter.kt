package com.owulia.makekotlin.base

interface IBasePresenter<V : IBaseView> {

    /**
     * 挂载 view
     * */
    fun attachView (view: V)

    /**
     * 卸载 view
     * */
    fun detachView ()

}
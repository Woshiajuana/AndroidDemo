package com.owulia.makekotlin.base

interface IBasePresenter {

    /**
     * 挂载 view
     * */
    fun attachView (view: IBaseView)

    /**
     * 卸载 view
     * */
    fun detachView ()

}
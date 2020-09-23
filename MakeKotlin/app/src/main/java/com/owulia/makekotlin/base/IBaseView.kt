package com.owulia.makekotlin.base

import android.app.Activity

interface IBaseView {

    /**
     * Presenter 中获取上下文对象
     * */
    fun getSelfActivity () : Activity

}
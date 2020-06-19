package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.base.IBasePresenter
import com.owulia.taobaounion.view.ITickCallback

interface ITickPresenter : IBasePresenter<ITickCallback> {
    fun getTicket(title: String, url: String, cover: String)
}
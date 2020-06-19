package com.owulia.taobaounion.view

import com.owulia.taobaounion.base.IBaseCallback
import com.owulia.taobaounion.model.domain.TicketResult

interface ITickCallback : IBaseCallback {

    // 加载淘宝口令的接口
    fun onTicketLoaded (cover: String, result: TicketResult)
}
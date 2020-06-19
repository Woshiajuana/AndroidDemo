package com.owulia.taobaounion.ui.activity

import com.bumptech.glide.Glide
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseActivity
import com.owulia.taobaounion.model.domain.TicketResult
import com.owulia.taobaounion.presenter.impl.TickPresenterImpl
import com.owulia.taobaounion.utils.PresenterManager
import com.owulia.taobaounion.view.ITickCallback
import kotlinx.android.synthetic.main.activity_ticket.*

class TicketActivity : BaseActivity(), ITickCallback {

    private var tickPresenter: TickPresenterImpl? = null

    override fun getLayoutResId () = R.layout.activity_ticket

    override fun initPresenter() {
        super.initPresenter()
        tickPresenter = PresenterManager.instant.tickPresenterImpl
        tickPresenter?.registerViewCallback(this)
    }

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()
        backBtn.setOnClickListener { finish() }
    }

    override fun onTicketLoaded(cover: String, result: TicketResult) {
        Glide.with(this).load(cover).into(tickerCover)
        tickerCode.setText(result.data.tbk_tpwd_create_response.data.model)
    }

    override fun onNetworkError() {
//        TODO("Not yet implemented")
    }

    override fun onLoading() {
//        TODO("Not yet implemented")
    }

    override fun onEmpty() {
//        TODO("Not yet implemented")
    }

    override fun release () {
        tickPresenter?.unregisterViewCallback(this)
    }
}

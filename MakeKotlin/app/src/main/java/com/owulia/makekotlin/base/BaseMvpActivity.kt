package com.owulia.makekotlin.base

import android.app.Activity
import android.os.Bundle

abstract class BaseMvpActivity<P : IBasePresenter> : BaseActivity (), IBaseView {

    var mvpPre: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpPre = bindPresenter()
        mvpPre?.attachView(this)
    }

    abstract fun bindPresenter () : P

    override fun getSelfActivity(): Activity {
        return this
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpPre?.detachView()
        mvpPre = null
    }

}
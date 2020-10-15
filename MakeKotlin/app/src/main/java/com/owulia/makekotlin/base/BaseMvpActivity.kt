package com.owulia.makekotlin.base

import android.app.Activity
import android.text.TextUtils
import com.owulia.makekotlin.widget.WowLoadingDialog
import com.owulia.makekotlin.utils.WowToastUtils

abstract class BaseMvpActivity<P : IBasePresenter> : BaseActivity (), IBaseView {

    var mvpPresenter: P? = null

    /**
     * loading 弹窗
     * */
    val vDialogLoading: WowLoadingDialog by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { WowLoadingDialog(this) }

    override fun initPresenter() {
        super.initPresenter()
        mvpPresenter = bindPresenter()
        mvpPresenter?.attachView(this)
    }

    abstract fun bindPresenter () : P

    override fun getSelfActivity(): Activity {
        return this
    }

    override fun loadingShow() {
        vDialogLoading.show()
    }

    override fun loadingDismiss() {
        vDialogLoading.dismiss()
    }

    override fun toast(msg: String) {
        if (!TextUtils.isEmpty(msg)){
            WowToastUtils.show(msg)
        }
    }

    override fun toast(msg: Int) {
        WowToastUtils.show(getString(msg))
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpPresenter?.detachView()
        mvpPresenter = null
    }

}
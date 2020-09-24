package com.owulia.makekotlin.widget

import android.app.Dialog
import android.content.Context
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_loading_dialog.*
import java.lang.Exception

class WowLoadingDialog(context: Context) : Dialog(context, R.style.Dialog_Theme_Loading) {

    companion object {
        private var mContext: Context? = null
        private var mInstance: WowLoadingDialog? = null
        fun init (context: Context) {
            mContext = context
        }
        fun getInstance () = mInstance ?: synchronized(this) {
            if (mContext === null) {
                throw Exception("WowJsonCacheUtils need Application context. You Should init ...")
            }
            WowLoadingDialog(mContext!!).also { mInstance = it }
        }
    }

    var mRotateAnimation: RotateAnimation? = null

    init {
        initView()
        initAnim()
    }

    /**
     * 装载 View
     * */
    private fun initView () {
//        val view = LayoutInflater.from(context).inflate(R.layout.widget_loading_dialog, null)
        setContentView(R.layout.widget_loading_dialog)
    }

    /**
     * 初始化动画
     * */
    private fun initAnim() {
        mRotateAnimation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 2000
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
            repeatMode = Animation.RESTART
            startTime = Animation.START_ON_FIRST_FRAME.toLong()
        }
    }

    override fun show() {
        super.show()
        WowToastUtils.show("show")
        vLoadingIcon.startAnimation(mRotateAnimation)
    }

    override fun dismiss() {
        WowToastUtils.show("dismiss")
        mRotateAnimation?.cancel()
        super.dismiss()
    }

}
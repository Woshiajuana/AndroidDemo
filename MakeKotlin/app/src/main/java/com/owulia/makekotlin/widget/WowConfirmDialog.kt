package com.owulia.makekotlin.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_confirm_dialog.*

class WowConfirmDialog(context: Context) : Dialog(context, R.style.Dialog_Theme_Confirm) {

    var setSureOnClickListener: ((View) -> Boolean?)? = null
    var setCancelOnClickListener: ((View) -> Boolean?)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_confirm_dialog)

        initView()

        initListener()
    }

    private fun initView () {

        /**
         * 设置弹窗动画
         * */
        window?.apply {

            /**
             * 设置弹窗内容位置
             * */
            setGravity(Gravity.CENTER)

            /**
             * 设置弹窗动画
             * */
//            setWindowAnimations(R.style.Dialog_Center_Zoom_In)
        }

    }

    private fun initListener () {
        vCancelButton.setOnClickListener { v ->
            setCancelOnClickListener?.let {
                val result = it(v)
                if (result == true) {
                    hide()
                }
            } ?: hide()
        }
        vSureButton.setOnClickListener { v ->
            setSureOnClickListener?.let {
                val result = it(v)
                if (result == true) {
                    hide()
                }
            } ?: hide()
        }
    }

}
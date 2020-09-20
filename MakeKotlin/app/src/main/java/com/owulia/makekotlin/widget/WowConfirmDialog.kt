package com.owulia.makekotlin.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_confirm_dialog.*

class WowConfirmDialog(context: Context) : Dialog(context, R.style.WowDialog) {

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
            setWindowAnimations(R.style.Dialog_Center_Zoom_In)
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

    /**
     * 获取 title view
     * */
    fun getTitleView () = vTitle

    /**
     * 获取 内容 view
     * */
    fun getMessageView () = vMessage

    /**
     * 获取 取消 view
     * */
    fun getCancelButtonView () = vCancelButton

    /**
     * 获取 sure view
     * */
    fun getSureButtonView () = vSureButton

    /**
     * 设置标题
     * */
    fun setTitle (text: String): WowConfirmDialog {
        vTitle.text = text
        return this
    }

    /**
     * 设置内容
     * */
    fun setMessage (text: String): WowConfirmDialog {
        vMessage.text = text
        return this
    }

    /**
     * 设置取消文字
     * */
    fun setCancelButton (text: String): WowConfirmDialog {
        vCancelButton.text = text
        return this
    }

    /**
     * 设置确认文字
     * */
    fun setSureButton (text: String): WowConfirmDialog {
        vSureButton.text = text
        return this
    }
}
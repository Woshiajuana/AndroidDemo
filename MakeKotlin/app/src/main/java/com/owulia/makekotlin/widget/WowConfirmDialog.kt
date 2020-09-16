package com.owulia.makekotlin.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_confirm_dialog.*

class WowConfirmDialog(context: Context) : Dialog(context, R.style.WowDialog) {

    val setSureOnClickListener: ((View) -> Boolean?)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_confirm_dialog)

        initView()

        initListener()
    }

    private fun initView () {

    }

    private fun initListener () {
        vCancelButton.setOnClickListener {
            hide()
        }
        vSureButton.setOnClickListener { v ->
            setSureOnClickListener?.let {
                val result = it(v)
                if (result == true) {
                    hide()
                }
            }
        }
    }

}
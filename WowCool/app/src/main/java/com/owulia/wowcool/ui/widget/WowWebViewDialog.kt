package com.owulia.wowcool.ui.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import com.owulia.wowcool.R

class WowWebViewDialog(context: Context) : Dialog(context, R.style.WowDialog) {

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_web_view, null)
        setContentView(view)
        view.layoutParams.apply {
            width = context.resources.displayMetrics.widthPixels
        }
        window?.apply {
            setGravity(Gravity.BOTTOM)
            setWindowAnimations(R.style.Dialog_Bottom_Animation)
        }
    }

    private fun initView () {

    }
}
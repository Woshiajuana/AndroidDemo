package com.owulia.makekotlin.widget

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import java.lang.Exception

class WowToastUtils private constructor(
    private val context: Context
) {

    companion object {

        private var mContext: Context? = null
        private var mToast: Toast? = null

        fun init (context: Context) {
            mContext = context
        }

        @SuppressLint("ShowToast")
        fun show (text: String, context: Context? = null) {
            val realContext = context?: mContext
            if (realContext != null) {
                if (mToast == null) {
                    mToast =  Toast.makeText(realContext, text, Toast.LENGTH_SHORT)
                } else {
                    mToast?.setText(text)
                }
                mToast?.show()
            } else {
                throw Exception("need init or context")
            }
        }
    }

}
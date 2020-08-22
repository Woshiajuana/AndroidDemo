package com.owulia.makekotlin.widget

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class WowLooperViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private var mHandle = Handler(Looper.getMainLooper())

    private var mTask = object : Runnable {
        override fun run() {
            currentItem++
            postDelayed(this, 3000)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startLooper()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopLooper()
    }

    private fun startLooper () {
        mHandle.postDelayed(mTask, 3000)
    }

    private fun stopLooper () {
        mHandle.removeCallbacks(mTask)
    }

}
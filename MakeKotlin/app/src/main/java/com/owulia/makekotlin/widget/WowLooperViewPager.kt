package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class WowLooperViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

//    private var mHandle = Handler(Looper.getMainLooper())

    var duration: Long = 3000

    init {
        setOnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    stopLooper()
                }
                MotionEvent.ACTION_CANCEL,
                MotionEvent.ACTION_UP -> {
                    startLooper()
                }
            }
            false
        }
    }

    private var mTask = object : Runnable {
        override fun run() {
            currentItem++
            postDelayed(this, duration)
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
        postDelayed(mTask, duration)
    }

    private fun stopLooper () {
        removeCallbacks(mTask)
    }

}
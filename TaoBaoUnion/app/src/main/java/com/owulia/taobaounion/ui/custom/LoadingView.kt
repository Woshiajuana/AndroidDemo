package com.owulia.taobaounion.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.owulia.taobaounion.R

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {


    private var mNeedRotate = false
    private var mDegrees = 0f

    init {
        setImageResource(R.mipmap.loading)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mNeedRotate = true
        startRotate()
    }

    private fun startRotate () {
        post(object : Runnable{
            override fun run() {
                mDegrees += 10f
                if (mDegrees >= 360f) {
                    mDegrees = 0f
                }
                invalidate()
                if (visibility != View.VISIBLE && !mNeedRotate) {
                    removeCallbacks(this)
                } else {
                    postDelayed(this, 10)
                }
            }
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopRotate()
    }

    private fun stopRotate () {
        mNeedRotate = false
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.rotate(mDegrees, width / 2f, height / 2f)
        super.onDraw(canvas)
    }

}
package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.owulia.makekotlin.R

class WowMaxHeightOrMaxCountRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    /**
     * 最大高度
     * */
    private var mMaxHeight: Int = -1

    /**
     * 最大个数
     * */
    private var mMaxCount: Int = -1

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.WowMaxHeightOrMaxCountRecyclerView)
            mMaxHeight = typedArray.getDimension(R.styleable.WowMaxHeightOrMaxCountRecyclerView_maxHeight, -1f).toInt()
            mMaxCount = typedArray.getInteger(R.styleable.WowMaxHeightOrMaxCountRecyclerView_maxCount, -1)
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        var heightMeasureSpec = heightSpec
        if (mMaxHeight > 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST)
        }
        if (mMaxCount > 0) {
            if (childCount > mMaxCount) {
                val child = getChildAt(0)
                child.measure(widthSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
                val height = child.measuredHeight + paddingTop + paddingBottom
                heightMeasureSpec = height * mMaxCount
                setMeasuredDimension(widthSpec, height * 5)
            }
        }
        super.onMeasure(widthSpec, heightMeasureSpec)
    }

}
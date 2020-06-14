package com.owulia.wowcool.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Scroller
import java.lang.IndexOutOfBoundsException
import kotlin.math.abs

class WowSlideMenuView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private val tag = "WowSlideMenuView"

    private lateinit var mvContentWrap: WowSlideContentWrap
    private lateinit var mvMenuWrap: WowSlideMenuWrap

    private var mDownX = 0f
    private var maxDX = 0 // 可移动最大边界

    private var mScroller: Scroller = Scroller(context)

    private var isOpen = false
    private var mCurrDirect = Direction.NONE

    enum class Direction {
        LEFT, RIGHT, NONE
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = event.x
                // 移动的差值
                val dx = (moveX - mDownX).toInt()

                mCurrDirect = if (dx > 0) Direction.RIGHT else Direction.LEFT

                val resDx = -dx + scrollX
                when {
                    resDx <= 0 -> {
                        scrollTo(0, 0)
                    }
                    resDx >= maxDX -> {
                        scrollTo(maxDX, 0)
                    }
                    else -> {
                        scrollBy(-dx, 0)
                    }
                }
                mDownX = moveX
            }
            MotionEvent.ACTION_UP -> {

                // 两个关注点 是否打开、 方向

                if (isOpen) {
                    // 当前状态打开
                    if (mCurrDirect == Direction.RIGHT) {
                        // 向右滑动 如果小于 3/4 就关闭
                        if (scrollX <= maxDX * 3 / 4) {
                            // 打开
                            close()
                        } else {
                            open()
                        }
                    } else if (mCurrDirect == Direction.LEFT) {
                        // 打开
                        open()
                    }
                } else {
                    // 当前关闭
                    if (mCurrDirect == Direction.LEFT) {
                        // 向左滑动
                        if (scrollX > maxDX / 4) {
                            // 打开
                            open()
                        } else {
                            close()
                        }
                    } else if (mCurrDirect == Direction.RIGHT) {
                        // 向右滑动
                        close()
                    }
                }

                // 处理释放之后 是显示还是收缩回去
                if (scrollX >= maxDX / 2) {
                } else {
                }
                invalidate()
            }
        }

//        requestLayout() 重新渲染页面
        return true
    }

    fun open () {
        isOpen = true
        mScroller.startScroll(scrollX, 0, maxDX - scrollX, 0, 500)
    }

    fun close () {
        isOpen = false
        mScroller.startScroll(scrollX, 0, -scrollX, 0, 500)
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            val currX = mScroller.currX
            scrollTo(currX, 0)
            invalidate()
        }
    }

    // 这里可以拿到自己的孩子
    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != 2) {
            throw IndexOutOfBoundsException("need two child")
        }
        Log.d(tag, "onFinishInflate 孩子多少个 => $childCount")
        // 取出孩子
        when (val childOne = getChildAt(0)) {
            is WowSlideContentWrap ->  mvContentWrap = childOne
            is WowSlideMenuWrap -> mvMenuWrap = childOne
        }
        when (val childTwo = getChildAt(1)) {
            is WowSlideContentWrap ->  mvContentWrap = childTwo
            is WowSlideMenuWrap -> mvMenuWrap = childTwo
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 父控件的大小
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        // 测量第一个内容部分
        // 宽度，跟父控件一样宽，高度有三种情况，如果指定大小，那么我们获取
        // 到它的大小，直接测了
        // 如果是包内容，at_most 如果是 match_parent，那就给它大小
        val contentHeight = mvContentWrap.layoutParams.height
        Log.d(tag, "contentHeight => $contentHeight")
        val contentHeightMeasureSpec =
            when (contentHeight) {
                LayoutParams.MATCH_PARENT -> MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
                LayoutParams.WRAP_CONTENT -> MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST)
                else -> MeasureSpec.makeMeasureSpec(contentHeight, MeasureSpec.EXACTLY)
            }
        mvContentWrap.measure(widthMeasureSpec, contentHeightMeasureSpec)

        // 拿到内容部分测量以后的高度
        val contentMeasureHeight = mvContentWrap.measuredHeight
        Log.d(tag, "contentMeasureHeight => $contentMeasureHeight")
        val menuWidth = mvMenuWrap.layoutParams.width
        Log.d(tag, "menuWidth => $contentHeight")
        // 测量编辑部分的宽度: 3/4 高度跟内容高度一样
        val menuWithMeasureSpec = MeasureSpec.makeMeasureSpec(menuWidth, MeasureSpec.AT_MOST)
        mvMenuWrap.measure(menuWithMeasureSpec, MeasureSpec.makeMeasureSpec(contentMeasureHeight, MeasureSpec.EXACTLY))
        maxDX = mvMenuWrap.measuredWidth
        Log.d(tag, "mvMenuWrap.measuredHeight => ${mvMenuWrap.measuredHeight}")
        Log.d(tag, "mvMenuWrap.measuredWidth => ${mvMenuWrap.measuredWidth}")

        // 测量自己
        // 宽度就是前面的总和，高度和内容一样

        setMeasuredDimension(widthSize + mvMenuWrap.measuredWidth, contentMeasureHeight)

    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        // 拜访内容
        val contentLeft = 0
        val contentTop = 0
        val contentRight = contentLeft + mvContentWrap.measuredWidth
        val contentBottom = contentTop + mvContentWrap.measuredHeight
        mvContentWrap.layout(contentLeft, contentTop, contentRight, contentBottom)

        // 拜访操作内容
        val menuLeft = contentRight
        val menuTop = 0
        val menuRight = menuLeft + mvMenuWrap.measuredWidth
        val menuBottom = menuTop + mvMenuWrap.measuredHeight
        mvMenuWrap.layout(menuLeft, menuTop, menuRight, menuBottom)

    }
}

class WowSlideMenuWrap @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }

}

class WowSlideContentWrap @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)
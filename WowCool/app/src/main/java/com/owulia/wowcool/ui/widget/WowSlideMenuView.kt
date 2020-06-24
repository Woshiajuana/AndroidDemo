package com.owulia.wowcool.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Scroller
import java.lang.IndexOutOfBoundsException
import kotlin.math.abs

class WowSlideMenuView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private lateinit var mvContentWrap: WowSlideContentWrap
    private lateinit var mvMenuWrap: WowSlideMenuWrap

    private var mDownX = 0f
    private var mMaxDX = 0 // 可移动最大边界

    private var mScroller: Scroller = Scroller(context)

    private var isOpen = false
    private var mCurrDirect = Direction.NONE

    private var mDuration = 500
    private var mInterceptDownX = 0f

    enum class Direction {
        LEFT, RIGHT, NONE
    }

    // 拦截事件
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                mInterceptDownX = ev.x
            }
            MotionEvent.ACTION_MOVE -> {
                if (ev.x - mInterceptDownX != 0f) {
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = event.x
                // 移动的差值
                val dx = (moveX - mDownX).toInt()

                if (dx != 0) {
                    mCurrDirect = if (dx > 0) Direction.RIGHT else Direction.LEFT
                }
                val resDx = -dx + scrollX
                when {
                    resDx <= 0 -> {
                        scrollTo(0, 0)
                    }
                    resDx >= mMaxDX -> {
                        scrollTo(mMaxDX, 0)
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
                        if (scrollX <= mMaxDX * 3 / 4) {
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
                        if (scrollX > mMaxDX / 4) {
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
            }
        }
//        requestLayout() 重新渲染页面
        return true
    }

    fun open () {
        isOpen = true
        val dx = mMaxDX - scrollX
        val duration = dx / (mMaxDX * 1f) * mDuration
        mScroller.startScroll(scrollX, 0, dx, 0, abs(duration.toInt()))
        invalidate()
    }

    fun close () {
        isOpen = false
        val dx = -scrollX
        val duration = dx / (mMaxDX * 1f) * mDuration
        mScroller.startScroll(scrollX, 0, dx, 0, abs(duration.toInt()))
        invalidate()
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
        val contentHeightMeasureSpec =
            when (val contentHeight = mvContentWrap.layoutParams.height) {
                LayoutParams.MATCH_PARENT -> MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
                LayoutParams.WRAP_CONTENT -> MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST)
                else -> MeasureSpec.makeMeasureSpec(contentHeight, MeasureSpec.EXACTLY)
            }
        mvContentWrap.measure(widthMeasureSpec, contentHeightMeasureSpec)

        // 拿到内容部分测量以后的高度
        val contentMeasureHeight = mvContentWrap.measuredHeight
        val menuWidth = mvMenuWrap.layoutParams.width
        // 测量编辑部分的宽度: 3/4 高度跟内容高度一样
        val menuWithMeasureSpec = MeasureSpec.makeMeasureSpec(menuWidth, MeasureSpec.AT_MOST)
        mvMenuWrap.measure(menuWithMeasureSpec, MeasureSpec.makeMeasureSpec(contentMeasureHeight, MeasureSpec.EXACTLY))
        mMaxDX = mvMenuWrap.measuredWidth

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
) : LinearLayout(context, attrs, defStyleAttr)

class WowSlideContentWrap @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)
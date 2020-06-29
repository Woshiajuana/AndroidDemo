package com.owulia.wowcool.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs

class WowSlideMenuView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var mDownX = 0f
    private var mMaxDX = 0 // 可移动最大边界

    private var mScroller: Scroller = Scroller(context)

    private var isOpen = false
    private var mCurrDirect = Direction.NONE

    private var mDuration = 500
    private var mInterceptDownX = 0f

    // 是否有手指触摸
    private var isTouching = false

    // 在打开状态下 是否点击了主体部分
    private var isClickContent = false

    companion object {
        // 存储打开
        var mStatusViewCache: WowSlideMenuView? = null
    }

    enum class Direction {
        LEFT, RIGHT, NONE
    }

    init {
        // 设置可以点击
        isClickable = true
    }

    // 分发事件
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // 有手指触摸，后续再有手指触模该 view 拦截事件
                if (isTouching) {
                    return false
                }
                isTouching = true
                mDownX = event.x
                mStatusViewCache?.let {
                    if (it != this) {
                        it.close()
                    }
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = event.x
                // 移动的差值
                val dx = (moveX - mDownX).toInt()

                if (dx != 0) {
                    mCurrDirect = if (dx > 0) Direction.RIGHT else Direction.LEFT
                }
                // 横向滑动超过10 或者 已经横向滚动超管10 认为用户要横向滚动，
                if (abs(dx) > 10 || abs(scrollX) > 10) {
                    parent.requestDisallowInterceptTouchEvent(true);
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
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                // 释放手指触摸标识
                isTouching = false
                // 两个关注点 是否打开、 方向
                if (isOpen) {
                    // 判断是否点击了主体部分
                    if (isClickContent) {
                        close()
                    } else if (mCurrDirect == Direction.RIGHT) {
                        // 当前状态打开
                        // 向右滑动 如果小于 4/5 就关闭
                        if (scrollX <= mMaxDX * 4 / 5) {
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
                        if (scrollX > mMaxDX / 5) {
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
        return super.dispatchTouchEvent(event)
    }

    // 拦截事件
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                mInterceptDownX = ev.x
            }
            MotionEvent.ACTION_MOVE -> {
                if (isOpen) {
                    // 打开状态下
                    // 先判断是否有横向移动
                    if (ev.x - mInterceptDownX == 0f) {
                        // 判断是否点击了主体部分
                        if (ev.x < width - scrollX - scrollX) {
                            // 点击了主体， 拦截事件
                            isClickContent = true
                            return true
                        }
                    } else {
                        return true
                    }
                } else {
                    // 非打开状态下，手指只要有横向移动 拦截事件
                    if (ev.x - mInterceptDownX != 0f) {
                        return true
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    fun open () {
        val dx = mMaxDX - scrollX
        val duration = dx / (mMaxDX * 1f) * mDuration
        mStatusViewCache = this
        isOpen = true
        mScroller.startScroll(scrollX, 0, dx, 0, abs(duration.toInt()))
        invalidate()
    }

    fun close () {
        val dx = -scrollX
        val duration = dx / (mMaxDX * 1f) * mDuration
        mStatusViewCache = null
        isClickContent = false
        isOpen = false
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
//    override fun onFinishInflate() {
//        super.onFinishInflate()
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (childCount <= 0) return
        // 父控件的大小
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        // 第一个子元素认为是主体部分
        // 测量第一个内容部分
        // 宽度，跟父控件一样宽，高度有三种情况，如果指定大小，那么我们获取
        // 到它的大小，直接测了
        // 如果是包内容，at_most 如果是 match_parent，那就给它大小
        val elContent = getChildAt(0)
        val contentHeightMeasureSpec =
            when (val contentHeight = elContent.layoutParams.height) {
                LayoutParams.MATCH_PARENT -> MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
                LayoutParams.WRAP_CONTENT -> MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST)
                else -> MeasureSpec.makeMeasureSpec(contentHeight, MeasureSpec.EXACTLY)
            }
        elContent.measure(widthMeasureSpec, contentHeightMeasureSpec)
        // 拿到内容部分测量剩下子元素
        val contentMeasureHeight = elContent.measuredHeight
        mMaxDX = 0
        for (index in 1 until childCount) {
            val elMenu = getChildAt(index)
            if (elMenu.visibility == View.VISIBLE) {
                val menuWidth = elMenu.layoutParams.width
                // 测量编辑部分的宽度: 3/4 高度跟内容高度一样
                val menuWithMeasureSpec = MeasureSpec.makeMeasureSpec(menuWidth, MeasureSpec.AT_MOST)
                elMenu.measure(menuWithMeasureSpec, MeasureSpec.makeMeasureSpec(contentMeasureHeight, MeasureSpec.EXACTLY))
                mMaxDX += elMenu.measuredWidth
            }
        }
        // 测量自己
        // 宽度就是前面的总和，高度和内容一样
        setMeasuredDimension(widthSize + mMaxDX, contentMeasureHeight)

    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0
        for (index in 0 until childCount) {
            val el = getChildAt(index)
            right = left + el.measuredWidth
            bottom = top + el.measuredHeight
            el.layout(left, top, right, bottom)
            left += el.measuredWidth
        }
    }
}
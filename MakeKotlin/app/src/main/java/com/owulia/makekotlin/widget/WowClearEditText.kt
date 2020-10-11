package com.owulia.makekotlin.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.widget.AppCompatEditText
import com.owulia.makekotlin.R

class WowClearEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    /**
     * 右边清除按钮
     * */
    private var mClearDrawable: Drawable? = null

    init {
        /**
         * 解决自定义输入框无法聚焦，唤起软键盘
         * */
        isFocusableInTouchMode = true

        /**
         * 右边清除按钮赋值
         * */
        mClearDrawable = compoundDrawables[2]
        if (mClearDrawable == null) {
            mClearDrawable = resources.getDrawable(R.drawable.ic_close_999)
        }
        mClearDrawable?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }

        /**
         * 默认设置隐藏图标
         * */
        setClearIconVisible(false)

        /**
         * 设置输入框内容发生改变的监听
         * */
        addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setClearIconVisible(s?.length?: 0 > 0)
            }
        })

    }

    /**
     * 因为不能直接给 EditText 设置点击事件，所以需要计算按下的位置来模拟点击事件
     * 当按下的位置在 EditText 的宽度 - 图标到控件右边的间距 - 图标的宽度 和
     * EditText 的宽度 - 图标到控件右边的间距之间，就算点击了图标，竖直方向不考虑
     * */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            if (action == MotionEvent.ACTION_UP) {
                if (mClearDrawable != null) {
                    val touchable = x > (width - totalPaddingEnd) && x < (width - paddingEnd)
                    if (touchable) {
                        setText("")
                        return false
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 设置清除图标的显示与隐藏
     * 调用 setCompoundDrawables 为 EditText 绘制上去
     * @param isVisible [Boolean]
     * */
    fun setClearIconVisible(isVisible: Boolean) {
        val right = if (isVisible) mClearDrawable else null
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], right, compoundDrawables[3])
    }

    /**
     * 设置输入框晃动动画
     * @param counts [Int]
     * */
    fun setShakeAnimation (counts: Float = 5f) {
        val translateAnimation = TranslateAnimation(0f, 10f, 0f, 0f).apply {
            interpolator = CycleInterpolator(counts)
            duration = 1000
        }
        animation = translateAnimation
    }

}
package com.owulia.wowcool

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import org.w3c.dom.Text

class WowTabBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var viewInner: ViewPager
    private lateinit var viewBottom: LinearLayout
    private lateinit var viewDivider: View

    init {

        renderWrapView()

        renderInnerView()

        renderDividerView()

        renderBottomView()

        render()

    }

    // 初始化容器布局
    private fun renderWrapView() {
        orientation = VERTICAL
    }

    // 初始化 ViewPager
    private fun renderInnerView() {
        viewInner = ViewPager(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            .apply {
                weight = 1f
            }
        viewInner.apply {
            layoutParams = params
            setBackgroundColor(Color.parseColor("#ffff0000"))
        }
    }

    // 初始化分割线
    private fun renderDividerView() {
        viewDivider = View(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 1)
        viewDivider.apply {
            layoutParams = params
            setBackgroundColor(Color.parseColor("#ff00ff00"))
        }
    }

    // 初始化底部布局
    private fun renderBottomView() {
        viewBottom = LinearLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 100)
        viewBottom.apply {
            layoutParams = params
            orientation = HORIZONTAL
            setBackgroundColor(Color.parseColor("#fff2f2f2"))
        }
    }

    // 添加布局
    private fun render() {
        addView(viewInner)
        addView(viewDivider)
        addView(viewBottom)
    }

    fun setItem (icon: Int, iconSelect: Int, text: String, fragment: Fragment) {
        viewBottom.addView(WowTabBarItem(context))
        viewBottom.addView(WowTabBarItem(context))
        viewBottom.addView(WowTabBarItem(context))
        viewBottom.addView(WowTabBarItem(context))
    }

    fun setColor (color: Color, colorSelect: Color) {

    }

    fun build () {

    }

}

class WowTabBarItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        render()

        renderImage()

        renderText()

    }

    private fun renderImage () {
        val viewImageNormal = ImageView(context)
        val viewImageActive = ImageView(context)
        val paramsImage = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        viewImageNormal.apply {
            layoutParams = paramsImage
            setImageResource(R.mipmap.tab_bar_home_normal)
        }
        viewImageActive.apply {
            layoutParams = paramsImage
            setImageResource(R.mipmap.tab_bar_home_active)
        }

        val viewFragment = FrameLayout(context)
        val paramsFragment = LayoutParams(48, 48) .apply {
            gravity = Gravity.CENTER
        }
        viewFragment.apply {
            layoutParams = paramsFragment
            addView(viewImageActive)
            addView(viewImageNormal)
        }
        addView(viewFragment)
    }

    private fun renderText () {
        val text = TextView(context)
        text.text = "h哈哈哈"
        text.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        text.textSize = 10f
        addView(text)
    }

    private fun render () {
        val params = LayoutParams(0, LayoutParams.MATCH_PARENT).apply {
            weight = 1f
        }
        layoutParams = params
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }


}
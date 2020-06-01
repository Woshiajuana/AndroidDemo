package com.owulia.wowcool.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.owulia.wowcool.R

class WowTabBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var viewInner: ViewPager
    private lateinit var viewBottom: LinearLayout
    private lateinit var viewDivider: View
    private var arrTabBarItem = mutableListOf<WowTabBarItem>()
    // 普通图片
    private var arrIconNormal = mutableListOf<Int>()
    // 选中图片
    private var arrIconActive = mutableListOf<Int>()
    // 文字
    private var arrTextItem = mutableListOf<String>()
    // Fragment
    private var arrFragment = mutableListOf<Fragment>()
    // 文字颜色
    private var colorTextNormal: Int? = null
    private var colorTextActive: Int? = null
    private var intTextSize: Int? = null

    init {
        orientation = VERTICAL

        renderInnerView()

        renderDividerView()

        renderBottomView()
    }

    // 初始化 ViewPager
    @SuppressLint("ResourceType")
    private fun renderInnerView() {
        viewInner = ViewPager(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            .apply {
                weight = 1f
            }
        viewInner.apply {
            layoutParams = params
            id = 1213141516
            setBackgroundColor(Color.parseColor("#ffff0000"))
        }
        addView(viewInner)
    }

    // 初始化分割线
    private fun renderDividerView() {
        viewDivider = View(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 1)
        viewDivider.apply {
            layoutParams = params
            setBackgroundColor(Color.parseColor("#ff00ff00"))
        }
        addView(viewDivider)
    }

    // 初始化底部布局
    private fun renderBottomView() {
        viewBottom = LinearLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 150)
        viewBottom.apply {
            layoutParams = params
            orientation = HORIZONTAL
            setBackgroundColor(Color.parseColor("#fff2f2f2"))
        }
        addView(viewBottom)
    }

    // 设置 adapter
    private fun setViewPagerAdapter(fragmentManager: FragmentManager) {
        viewInner.apply {
            offscreenPageLimit = arrIconNormal.size
            adapter = object : FragmentPagerAdapter(fragmentManager) {
                override fun getItem(position: Int): Fragment {
                    return arrFragment[position]
                }

                override fun getCount(): Int {
                    return arrTextItem.size
                }
            }
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (positionOffset > 0) {
                        val left = arrTabBarItem[position]
                        val right = arrTabBarItem[position + 1]
                        left.setProgress(1 - positionOffset)
                        right.setProgress(positionOffset)
                    }
                }

                override fun onPageSelected(position: Int) {}
            })
        }
    }

    // 添加wowTabBarItem
    fun addedItem(icon: Int, iconSelect: Int, text: String, fragment: Fragment): WowTabBar {
        arrIconNormal.add(icon)
        arrIconActive.add(iconSelect)
        arrTextItem.add(text)
        arrFragment.add(fragment)
        return this
    }

    // 设置文字
    fun setItemText(color: Int, colorSelect: Int, textSize: Int? = null): WowTabBar {
        colorTextNormal = color
        colorTextActive = colorSelect
        intTextSize = textSize
        return this
    }

    // 切换页面
    fun switchItem(item: Int, smoothScroll: Boolean = false): WowTabBar {
        viewInner.setCurrentItem(item, smoothScroll)
        arrTabBarItem.forEachIndexed { index, it ->
            it.setProgress(if (index == item) 1f else 0f)
        }
        return this
    }

    // build
    fun build(fragmentManager: FragmentManager): WowTabBar {
        arrIconNormal.forEachIndexed { index: Int, item: Int ->
            val wowTabBarItem = WowTabBarItem(
                context,
                item,
                arrIconActive[index],
                arrTextItem[index],
                colorTextNormal,
                colorTextActive,
                null
            )
            arrTabBarItem.add(wowTabBarItem)
            viewBottom.addView(wowTabBarItem)
        }
        setViewPagerAdapter(fragmentManager)
        arrTabBarItem.forEachIndexed { index, item ->
            item.setOnClickListener {
                switchItem(index)
            }
        }
        return this
    }

}

class WowTabBarItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var colorTextNormalDefault = Color.parseColor("#ff000000")
    private var colorTextActiveDefault = Color.parseColor("#ff007FD6")

    private var iconNormal: Int = 0
    private var iconActive: Int = 0
    private var textItem: String = ""
    private var textSize: Int = 0
    private var colorTextNormal = colorTextNormalDefault
    private var colorTextActive = colorTextActiveDefault

    private lateinit var viewImageNormal: ImageView
    private lateinit var viewImageActive: ImageView
    private lateinit var viewText: TextView

    init {
        renderWrapView()
    }

    constructor(
        context: Context,
        icon: Int,
        iconSelect: Int,
        text: String,
        color: Int?,
        colorSelect: Int?,
        textSize: Int?
    ) : this(context) {
        iconNormal = icon
        iconActive = iconSelect

        textItem = text
        colorTextNormal = color?: colorTextNormalDefault
        colorTextActive = colorSelect?: colorTextActiveDefault
//        textSize =

        renderImageView()
        renderTextView()

        setProgress(0f)
    }

    fun px2dp(px: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        px,
        Resources.getSystem().displayMetrics
    )

    private fun renderWrapView() {
        val params = LayoutParams(0, LayoutParams.MATCH_PARENT).apply {
            weight = 1f
        }
        layoutParams = params
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    private fun renderImageView() {
        viewImageNormal = ImageView(context)
        viewImageActive = ImageView(context)
        val paramsImage = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        viewImageNormal.apply {
            layoutParams = paramsImage
            setImageResource(iconNormal)
        }
        viewImageActive.apply {
            layoutParams = paramsImage
            setImageResource(iconActive)
        }

        val viewFragment = FrameLayout(context)
        val paramsFragment = LayoutParams(60, 60).apply {
            gravity = Gravity.CENTER
        }
        viewFragment.apply {
            layoutParams = paramsFragment
            addView(viewImageActive)
            addView(viewImageNormal)
        }
        addView(viewFragment)
    }

    private fun renderTextView() {
        viewText = TextView(context)
        viewText.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            .apply {
                topMargin = 10
            }
        viewText.apply {
            textSize = resources.getDimension(R.dimen.dimen_tab_bar_text_size)
            text = textItem
            setTextColor(colorTextNormal)
        }
        addView(viewText)
    }

    fun setProgress(progress: Float) {
        viewImageNormal.alpha = 1 - progress
        viewImageActive.alpha = progress
        viewText.setTextColor(evaluate(progress, colorTextNormal, colorTextActive))
    }

    private fun evaluate(progress: Float, startValue: Int, endValue: Int): Int {
        val startA = startValue shr 24 and 0xff
        val startR = startValue shr 16 and 0xff
        val startG = startValue shr 8 and 0xff
        val startB = startValue and 0xff

        val endA = endValue shr 24 and 0xff
        val endR = endValue shr 16 and 0xff
        val endG = endValue shr 8 and 0xff
        val endB = endValue and 0xff

        return (startA + progress * (endA - startA)).toInt() shl 24 or (
                (startR + progress * (endR - startR)).toInt() shl 16) or (
                (startG + progress * (endG - startG)).toInt() shl 8) or
                (startB + progress * (endB - startB)).toInt()
    }

}

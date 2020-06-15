package com.owulia.wowcool.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

class WowTabBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    // 定义基础 view 变量
    private lateinit var vpTabBarInner: ViewPager
    private lateinit var llTabBarBottom: LinearLayout
    private lateinit var dvTabBarDivider: View

    // 定义
    private var arrTabBarItem = mutableListOf<WowTabBarItemView>()
    private var arrTabBarItemIconNormal = mutableListOf<Int>()
    private var arrTabBarItemIconActive = mutableListOf<Int>()
    private var arrTabBarItemText = mutableListOf<String>()
    public var arrTabBarFragment = mutableListOf<Fragment>()

    // 设置
    private var tabBarBottomHeight = px2dp(50f).toInt()
    private var tabBarBottomBackgroundColor = Color.parseColor("#ffffffff")
    private var tabBarDividerHeight = 1
    private var tabBarDividerBackgroundColor = Color.parseColor("#ffdddddd")
    private var tabBarItemIconSize: Int? = null
    private var tabBarItemTextColorNormal: Int? = null
    private var tabBarItemTextColorActive: Int? = null
    private var isUseDivider = true

    private val TAG = "wowtabbarview"

    init {
        orientation = VERTICAL
    }

    // 渲染布局
    @SuppressLint("ResourceType")
    private fun render() {
        vpTabBarInner = ViewPager(context)
        vpTabBarInner.apply {
            id = 1213141516
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                .apply {
                    weight = 1f
                }
        }
        addView(vpTabBarInner)

        if (isUseDivider) {
            dvTabBarDivider = View(context)
            dvTabBarDivider.apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, tabBarDividerHeight)
                setBackgroundColor(tabBarDividerBackgroundColor)
            }
            addView(dvTabBarDivider)
        }

        llTabBarBottom = LinearLayout(context)
        llTabBarBottom.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, tabBarBottomHeight)
            orientation = HORIZONTAL
            setBackgroundColor(tabBarBottomBackgroundColor)
        }

        addView(llTabBarBottom)

    }

    private fun addedTabBarItem() {
        arrTabBarItemIconNormal.forEachIndexed { index, icon ->
            val wowTabBarItemView = WowTabBarItemView(
                context,
                icon,
                arrTabBarItemIconActive[index],
                tabBarItemIconSize,
                arrTabBarItemText[index],
                tabBarItemTextColorNormal,
                tabBarItemTextColorActive
            )
            arrTabBarItem.add(wowTabBarItemView)
            llTabBarBottom.addView(wowTabBarItemView)
        }
    }

    private fun setViewPagerAdapter(
        fragmentManager: FragmentManager,
        onInstantiateFragment: ((Int, Fragment) -> Unit)? = null,
        onDestroyFragment: ((Int) -> Unit)? = null
    ) {
        Log.d(TAG, "setViewPagerAdapter")
        vpTabBarInner.apply {
            offscreenPageLimit = arrTabBarItemIconNormal.size
            adapter = object : FragmentStatePagerAdapter(fragmentManager) {
                override fun getItem(position: Int): Fragment {
                    Log.d(TAG, "getItem position => ${position}")
                    return arrTabBarFragment[position]
                }
                override fun getCount(): Int {
                    return arrTabBarItemIconNormal.size
                }
                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val fragment = super.instantiateItem(container, position) as Fragment
                    Log.d(TAG, "instantiateItem position => ${fragment}")
                    if (onInstantiateFragment != null) {
                        onInstantiateFragment(position, fragment)
                    }
                    return fragment
                }
                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    Log.d(TAG, "destroyItem position => ${position}")
                    if (onDestroyFragment != null) {
                        onDestroyFragment(position)
                    }
                    super.destroyItem(container, position, `object`)
                }
            }
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                    Log.d(TAG, "onPageScrollStateChanged => ${state}")
                }
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
//                    Log.d(TAG, "onPageScrollStateChanged => ${position}")

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

    // 添加WowTabBarItemView元素
    fun addItem(icon: Int, iconSelect: Int, text: String, fragment: Fragment): WowTabBarView {
        arrTabBarItemIconNormal.add(icon)
        arrTabBarItemIconActive.add(iconSelect)
        arrTabBarItemText.add(text)
        arrTabBarFragment.add(fragment)
        return this
    }

    // 设置分割线
    fun setDivider(isUse: Boolean = true, height: Int? = null, bgColor: Int? = null) {
        isUseDivider = isUse
        tabBarDividerHeight = height ?: tabBarDividerHeight
        tabBarBottomBackgroundColor = bgColor ?: tabBarBottomBackgroundColor
    }

    // 设置 tabBarItem 文字
    fun setItemText(color: Int? = null, colorSelect: Int? = null) {
        tabBarItemTextColorNormal = color
        tabBarItemTextColorActive = colorSelect
    }

    // 设置 tabBarItem icon
    fun setItemIcon(size: Int? = null) {
        tabBarItemIconSize = size
    }

    // 切换页面
    fun switchItem(item: Int, smoothScroll: Boolean = false): WowTabBarView {
        vpTabBarInner.setCurrentItem(item, smoothScroll)
        arrTabBarItem.forEachIndexed { index, it ->
            it.setProgress(if (index == item) 1f else 0f)
        }
        return this
    }

    // 构建
    fun build(
        fragmentManager: FragmentManager,
        onInstantiateFragment: ((Int, Fragment) -> Unit)? = null,
        onDestroyFragment: ((Int) -> Unit)? = null
    ): WowTabBarView {
        render()
        addedTabBarItem()
        setViewPagerAdapter(fragmentManager, onInstantiateFragment, onDestroyFragment)
        addClickEvent()
        switchItem(0)
        return this
    }

    private fun addClickEvent() {
        arrTabBarItem.forEachIndexed { index, item ->
            item.setOnClickListener {
                switchItem(index)
            }
        }
    }

    private fun px2dp(p: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        p,
        Resources.getSystem().displayMetrics
    )

}


private class WowTabBarItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var imgIconNormal: ImageView
    private lateinit var imgIconActive: ImageView
    private lateinit var tvText: TextView

    private var iconNormal = 0
    private var iconActive = 0
    private var iconSize = px2dp(20f).toInt()
    private var textColorNormal = Color.parseColor("#ff000000")
    private var textColorActive = Color.parseColor("#ff007FD6")
    private var strText = ""

    constructor (
        context: Context,
        icon: Int,
        iconSelect: Int,
        size: Int?,
        text: String,
        color: Int?,
        colorSelect: Int?
    ) : this(context) {
        iconNormal = icon
        iconActive = iconSelect
        iconSize = size ?: iconSize
        strText = text
        textColorNormal = color ?: textColorNormal
        textColorActive = colorSelect ?: textColorActive

        render()

        setProgress(0f)

    }

    private fun render() {
        val typedArray =
            context.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
        background = typedArray.getDrawable(0)
        typedArray.recycle()
        isClickable = true
        layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT).apply {
            weight = 1f
        }
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val paramsImage = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        imgIconNormal = ImageView(context).apply {
            layoutParams = paramsImage
            setImageResource(iconNormal)
        }
        imgIconActive = ImageView(context).apply {
            layoutParams = paramsImage
            setImageResource(iconActive)
        }

        val viewFragment = FrameLayout(context).apply {
            layoutParams = LayoutParams(iconSize, iconSize)
            addView(imgIconNormal)
            addView(imgIconActive)
        }
        addView(viewFragment)

        tvText = TextView(context).apply {
            textSize = 10f
            text = strText
            setTextColor(textColorNormal)
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                .apply {
                    topMargin = 4
                }
        }
        addView(tvText)
    }

    fun setProgress(progress: Float) {
        imgIconNormal.alpha = 1 - progress
        imgIconActive.alpha = progress
        tvText.setTextColor(evaluate(progress, textColorNormal, textColorActive))
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

    private fun px2dp(p: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        p,
        Resources.getSystem().displayMetrics
    )

}
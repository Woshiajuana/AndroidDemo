package com.owulia.wowcool.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
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
    private var arrTabBarFragment = mutableListOf<Fragment>()

    // 设置
    private var tabBarBottomHeight = px2dp(50f).toInt()
    private var tabBarBottomBackgroundColor = Color.parseColor("#ffffffff")
    private var tabBarDividerHeight = 1
    private var tabBarDividerBackgroundColor = Color.parseColor("#ffdddddd")
    private var tabBarItemIconSize: Int? = null
    private var tabBarItemTextColorNormal: Int? = null
    private var tabBarItemTextColorActive: Int? = null
    private var isUseDivider = true

    init {
        orientation = VERTICAL
    }

    // 渲染布局
    @SuppressLint("ResourceType")
    fun render () {
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

    // 添加WowTabBarItemView元素
    fun addItem (icon: Int, iconSelect: Int, text: String, fragment: Fragment) : WowTabBarView {
        arrTabBarItemIconNormal.add(icon)
        arrTabBarItemIconActive.add(iconSelect)
        arrTabBarItemText.add(text)
        arrTabBarFragment.add(fragment)
        return this
    }

    // 设置分割线
    fun setDivider (isUse: Boolean = true, height: Int? = null, bgColor: Int? = null) {
        isUseDivider = isUse
        tabBarDividerHeight = height?: tabBarDividerHeight
        tabBarBottomBackgroundColor = bgColor?: tabBarBottomBackgroundColor
    }

    // 设置 tabBarItem 文字
    fun setItemText (color: Int? = null, colorSelect: Int? = null) {
        tabBarItemTextColorNormal = color
        tabBarItemTextColorActive = colorSelect
    }

    // 设置 tabBarItem icon
    fun setItemIcon (size: Int? = null) {
        tabBarItemIconSize = size
    }

    // 构建
    fun build () : WowTabBarView {
        render()
        return this
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

    

}
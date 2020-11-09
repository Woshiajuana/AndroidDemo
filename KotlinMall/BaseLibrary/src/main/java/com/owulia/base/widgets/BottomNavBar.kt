package com.owulia.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.owulia.base.R

class BottomNavBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val mCartBadge: TextBadgeItem
    private val mMsgBadge: ShapeBadgeItem

    init {
        // 首页
        val homeItem = BottomNavigationItem(
            R.drawable.btn_nav_home_press,
            resources.getString(R.string.nav_bar_home)
        ).apply {
            setInactiveIconResource(R.drawable.btn_nav_home_normal)
            setActiveColorResource(R.color.common_blue)
            setInActiveColorResource(R.color.text_normal)
        }

        // 分类
        val categoryItem = BottomNavigationItem(
            R.drawable.btn_nav_category_press,
            resources.getString(R.string.nav_bar_category)
        ).apply {
            setInactiveIconResource(R.drawable.btn_nav_category_normal)
            setActiveColorResource(R.color.common_blue)
            setInActiveColorResource(R.color.text_normal)
        }

        // 购物车
        val cartItem = BottomNavigationItem(
            R.drawable.btn_nav_cart_press,
            resources.getString(R.string.nav_bar_cart)
        ).apply {
            setInactiveIconResource(R.drawable.btn_nav_cart_normal)
            setActiveColorResource(R.color.common_blue)
            setInActiveColorResource(R.color.text_normal)
        }
        mCartBadge = TextBadgeItem()
        cartItem.setBadgeItem(mCartBadge)

        // 消息
        val msgItem = BottomNavigationItem(
            R.drawable.btn_nav_msg_press,
            resources.getString(R.string.nav_bar_msg)
        ).apply {
            setInactiveIconResource(R.drawable.btn_nav_msg_normal)
            setActiveColorResource(R.color.common_blue)
            setInActiveColorResource(R.color.text_normal)
        }
        mMsgBadge = ShapeBadgeItem()
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgItem.setBadgeItem(mMsgBadge)

        // 我的
        val userItem = BottomNavigationItem(
            R.drawable.btn_nav_user_press,
            resources.getString(R.string.nav_bar_user)
        ).apply {
            setInactiveIconResource(R.drawable.btn_nav_user_normal)
            setActiveColorResource(R.color.common_blue)
            setInActiveColorResource(R.color.text_normal)
        }

        setMode(MODE_FIXED)
        setBackgroundStyle(BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)


        addItem(homeItem)
        addItem(categoryItem)
        addItem(cartItem)
        addItem(msgItem)
        addItem(userItem)
        setFirstSelectedPosition(0)

        initialise()
    }

    fun checkCartBadge (count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("$count")
        }
    }

    fun checkMsgBadge (isVisible: Boolean) {
        if (isVisible) {
            mMsgBadge.show()
        } else {
            mMsgBadge.hide()
        }
    }


}
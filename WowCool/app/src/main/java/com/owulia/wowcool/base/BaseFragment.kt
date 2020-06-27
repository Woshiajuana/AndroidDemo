package com.owulia.wowcool.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.ui.widget.NavBarView
import com.owulia.wowcool.utils.WowSizeUtils
import com.owulia.wowcool.utils.WowStatusBarUtils
import com.owulia.wowcool.utils.WowToastUtils

abstract class BaseFragment : Fragment() {

    var vRootView: ViewGroup? = null
    var vWrapView: View? = null
    var vNavBar: NavBarView? = null
    var vStatusBar: View? = null

    // 是否使用状态栏占位符
    open val isUseStatusBarSeat: Boolean = true
    // 是否使用导航栏
    open val isUseNavBar: Boolean = true

    // 标题
    open val mNavBarTitle: Int = -1
    // 左边返回按钮
    open val mNavBarLeftImage: Int = R.drawable.ic_arrow
    // 右边按钮
    open val mNavBarRightImage: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vRootView = inflater.inflate(getRootViewResourceId(), container, false) as ViewGroup
        vWrapView = inflater.inflate(getViewResourceId(), container, false)
        // 设置状态栏
        if (isUseStatusBarSeat) {
            initStatusBarSeat()
        }
        // 设置导航栏
        if (isUseNavBar) {
            initNavBar()
        }
        vRootView?.addView(vWrapView)
        return vRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 初始化 view
        initView(vRootView!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        release()
    }

    open fun initNavBar () {
        vNavBar = NavBarView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                WowSizeUtils.px2dp(44f).toInt()
            )
            setBackgroundColor(Color.parseColor("#ffffff"))
            setTitle(if (mNavBarTitle == -1) "" else getString(mNavBarTitle))
            setLeftImage(mNavBarLeftImage)
            setRightImage(mNavBarRightImage)
        }
        val index = if (isUseStatusBarSeat) 1 else 0
        vRootView?.addView(vNavBar, index)
    }

    open fun initStatusBarSeat() {
        vStatusBar = WowStatusBarUtils.setStatusBarSeat(requireContext(), vRootView!! as ViewGroup).apply {
            setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }

    // 设置标题居左
    open fun setNavBarTitleLeftAlign () {
        vNavBar?.findViewById<TextView>(R.id.vNavBarTitle)?.apply {
            (layoutParams as RelativeLayout.LayoutParams).apply {
                if (mNavBarLeftImage == -1) {
                    addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                } else {
                    addRule(RelativeLayout.RIGHT_OF, R.id.vNavBarLeftBtn)
                }
            }
        }
    }

    /**
     * 工具类弹窗
     * @param text [String] 弹窗文案
     * @param context [Context] 上下文、非必填
     * */
    fun utilToast (text: String, context: Context? = null) {
        WowToastUtils.show(text, context)
    }

    /**
     * @return rootView id
     * */
    fun getRootViewResourceId() : Int = R.layout.fragment_base

    /**
     * @return rootView id
     * */
    abstract fun getViewResourceId() : Int

    /**
     * @param view [View]
     * */
    open fun initView(view: View) {}

    /**
     * 释放资源
     * */
    open fun release() {}

}
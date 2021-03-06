package com.owulia.wowcool.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
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
        // 初始化 事件
        initEvent()
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
            setOnLeftBtnClickListener = {
                this@BaseFragment.activity?.onBackPressed()
            }
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
            gravity = Gravity.LEFT
            (layoutParams as RelativeLayout.LayoutParams).apply {
                if (mNavBarLeftImage == -1) {
                    addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                } else {
                    addRule(RelativeLayout.RIGHT_OF, R.id.vNavBarLeftBtn)
                }
            }
        }
    }

    // 工具类弹窗
    fun utilToast (text: String, context: Context? = null) {
        WowToastUtils.show(text, context)
    }

    // 获取 root 页面资源
    open fun getRootViewResourceId() : Int = R.layout.fragment_base

    // 获取挂载的页面资源
    abstract fun getViewResourceId() : Int

    // 初始化 view
    open fun initView(view: View) {}

    // 初始化事件
    open fun initEvent() {}

    // 释放资源
    open fun release() {}

    // 物理返回键 拦截返回键返回true，不拦截返回false
    open fun onBackPressed(): Boolean = false

    // 跳转
    open fun routerPush(resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
        Navigation.findNavController(requireActivity(), R.id.fragment).navigate(resId, args, navOptions)
    }

}
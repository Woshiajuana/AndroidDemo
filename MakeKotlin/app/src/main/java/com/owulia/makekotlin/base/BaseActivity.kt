package com.owulia.makekotlin.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.utils.WowSizeUtils
import com.owulia.makekotlin.utils.WowStatusBarUtils
import com.owulia.makekotlin.widget.NavBarView
import kotlinx.android.synthetic.main.fragment_base.*

abstract class BaseActivity : AppCompatActivity() {

    /**
     * 布局根 view
     * */
    var vRootView: ViewGroup? = null

    /**
     * 加载失败 view
     * */
    var vErrorView: ViewGroup? = null

    /**
     * 加载成功 view
     * */
    var vContentView: ViewGroup? = null

    /**
     * 加载中 view
     * */
    var vLoadingView: ViewGroup? = null

    /**
     * 无数据 view
     * */
    var vEmptyView: ViewGroup? = null

    /**
     * 渲染状态
     * */
    enum class RenderState {
        NONE,
        LOADING,
        ERROR,
        EMPTY,
        SUCCESS,
    }

    /**
     * 导航栏 view
     * */
    var vNavBar: NavBarView? = null

    /**
     * 状态栏 View
     * */
    var vStatusBar: View? = null

    /**
     * 是否使用状态栏占位符
     * */
    open val isUseStatusBarSeat: Boolean = true

    /**
     * 状态栏是否亮色模式
     * */
    open val isStatusBarLightMode = true

    /**
     * 是否使用导航栏
     * */
    open val isUseNavBar: Boolean = true

    /**
     * 标题
     * */
    open val mNavBarTitle: Int = -1

    /**
     * 左边按钮
     * */
    open val mNavBarLeftImg: Int = R.drawable.ic_arrow_back_1

    /**
     * 右边文字按钮
     * */
    open val mNavBarRightText: Int = -1

    /**
     * 右边按钮
     * */
    open val mNavBarRightImg: Int = -1

    /**
     * presenter
     * */
//    open var presenter : P? = null

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 初始化 ViewGroup
         * */
        vRootView =
            LayoutInflater.from(this).inflate(getRootViewResourceId(), null, false) as ViewGroup
        setContentView(vRootView)
        vErrorView =
            LayoutInflater.from(this).inflate(getErrorViewResourceId(), null, false) as ViewGroup
        vLoadingView =
            LayoutInflater.from(this).inflate(getLoadingViewResourceId(), null, false) as ViewGroup
        vEmptyView =
            LayoutInflater.from(this).inflate(getEmptyViewResourceId(), null, false) as ViewGroup
        vContentView =
            LayoutInflater.from(this).inflate(getContentViewResourceId(), null, false) as ViewGroup

        /**
         * 载入基础 viewGroup
         * */
        renderView()

        /**
         * 渲染 viewGroup
         * */
        render()

        /**
         * 设置状态栏占位
         * */
        if (isUseStatusBarSeat) {
            initStatusBarSeat()
        }

        /**
         * 设置导航栏
         * */
        if (isUseNavBar) {
            initNavBar()
        }

        /**
         * 初始化 view
         * */
        initView()

        /**
         * 初始化事件
         * */
        initListener()
    }

    override fun onStart() {
        super.onStart()
        /**
         * 设置状态栏亮色模式
         * */
        if (isStatusBarLightMode) {
            setStatusBarLightMode()
        } else {
            setStatusBarDarkMode()
        }
    }

    /**
     * 载入基础 viewGroup
     * */
    open fun renderView() {
        vContainer.apply {
            addView(vLoadingView)
            addView(vErrorView)
            addView(vEmptyView)
            addView(vContentView)
        }
    }

    /**
     * 渲染
     * */
    open fun render(state: RenderState = RenderState.NONE) {
        vContentView?.visibility = if (state == RenderState.SUCCESS) View.VISIBLE else View.GONE
        vLoadingView?.visibility = if (state == RenderState.LOADING) View.VISIBLE else View.GONE
        vErrorView?.visibility = if (state == RenderState.ERROR) View.VISIBLE else View.GONE
        vEmptyView?.visibility = if (state == RenderState.EMPTY) View.VISIBLE else View.GONE
    }

    /**
     * 设置导航栏
     * */
    open fun initNavBar() {
        vNavBar = NavBarView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                WowSizeUtils.px2dp(44f).toInt()
            )
            setBackgroundColor(Color.parseColor("#ffffff"))
            setTitle(if (mNavBarTitle == -1) "" else getString(mNavBarTitle))
            setRightTextBtn(if (mNavBarRightText == -1) "" else getString(mNavBarRightText))
            setLeftImgBtn(mNavBarLeftImg)
            setRightImgBtn(mNavBarRightImg)
            setOnLeftBtnClickListener = {
                finish()
            }
        }
        val index = if (isUseStatusBarSeat) 1 else 0
        vRootView?.addView(vNavBar, index)
    }

    /**
     * 设置状态栏占位，沉浸式状态栏
     * */
    open fun initStatusBarSeat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            /**
             * 设置全屏，状态栏悬浮
             * */
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            /**
             * 占位 view
             * */
            vStatusBar = View(this).apply {
                setBackgroundColor(ContextCompat.getColor(this@BaseActivity, android.R.color.white))
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    WowStatusBarUtils.getStatusBarHeight(this@BaseActivity)
                )
            }
            vRootView?.addView(vStatusBar, 0)
        }
    }

    /**
     * 状态栏亮色模式，设置状态栏黑色文字、图标
     * */
    fun setStatusBarLightMode () {
        WowStatusBarUtils.setStatusBarLightMode(this)
    }

    /**
     * 状态暗色模式
     * */
    fun setStatusBarDarkMode () {
        WowStatusBarUtils.setStatusBarDarkMode(this)
    }

    /**
     * 初始化 view
     * */
    open fun initView() {}

    /**
     * 初始化事件
     * */
    open fun initListener() {}

    /**
     * 获取 base View id
     * */
    open fun getRootViewResourceId() = R.layout.fragment_base

    /**
     * 获取 error View id
     * */
    open fun getErrorViewResourceId() = R.layout.fragment_error

    /**
     * 获取 loading View id
     * */
    open fun getLoadingViewResourceId() = R.layout.fragment_loading

    /**
     * 获取 null View id
     * */
    open fun getEmptyViewResourceId() = R.layout.fragment_empty

    /**
     * 获取主题内容 View id
     * */
    abstract fun getContentViewResourceId(): Int

}
package com.owulia.makekotlin.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.owulia.makekotlin.R
import com.owulia.makekotlin.widget.NavBarView

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
    var vNullView: ViewGroup? = null

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
     * 右边按钮
     * */
    open val mNavBarRightImg: Int = -1

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vRootView = LayoutInflater.from(this).inflate(getRootViewResourceId(), null, false) as ViewGroup
        vErrorView = LayoutInflater.from(this).inflate(getErrorViewResourceId(), null, false) as ViewGroup
        vLoadingView = LayoutInflater.from(this).inflate(getLoadingViewResourceId(), null, false) as ViewGroup
        vNullView = LayoutInflater.from(this).inflate(getNullViewResourceId(), null, false) as ViewGroup
        vContentView = LayoutInflater.from(this).inflate(getContentViewResourceId(), null, false) as ViewGroup

    }

    /**
     * 获取 base View id
     * */
    open fun getRootViewResourceId () = R.layout.fragment_base

    /**
     * 获取 error View id
     * */
    open fun getErrorViewResourceId () = R.layout.fragment_error

    /**
     * 获取 loading View id
     * */
    open fun getLoadingViewResourceId () = R.layout.fragment_loading

    /**
     * 获取 null View id
     * */
    open fun getNullViewResourceId () = R.layout.fragment_null

    /**
     * 获取主题内容 View id
     * */
    abstract fun getContentViewResourceId () : Int




}
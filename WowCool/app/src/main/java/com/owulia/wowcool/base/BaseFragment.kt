package com.owulia.wowcool.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.utils.WowStatusBarUtils
import com.owulia.wowcool.utils.WowToastUtils

abstract class BaseFragment : Fragment() {

    private var vRootView: ViewGroup? = null
    private var vWrapView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vRootView = inflater.inflate(getRootViewResourceId(), container, false) as ViewGroup
        vWrapView = inflater.inflate(getViewResourceId(), container, false)
        vRootView?.addView(vWrapView)
        return vRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 设置状态栏
        initStatusBarSeat()
        // 初始化 view
        initView(vRootView!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        release()
    }

    open fun initStatusBarSeat() {
        WowStatusBarUtils.setStatusBarSeat(requireContext(), vRootView!! as ViewGroup)
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
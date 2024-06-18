package com.ohshell.headline.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {
    /**
     * 初始化页面
     * */
    protected open fun initView() {}


    /**
     * 初始化数据
     * */
    protected open fun initData() {}


    /**
     * 设置监听器
     * */
    protected open fun initListeners () {}

    /**
     * 返回要显示的界面
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = getLayoutView(inflater, container, savedInstanceState)
        
        return view
    }

    abstract fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?


    /**
     * 在 onCreate 方法后面调用
     * @param savedInstanceState
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListeners()
    }
}
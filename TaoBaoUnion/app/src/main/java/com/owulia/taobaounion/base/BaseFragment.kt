package com.owulia.taobaounion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getRootViewResId(), container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(rootView)
        initPresenter()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        release()
    }

    // 初始化 view
    open fun initView (view: View) {}

    // 释放资源 presenter
    open fun release () {}

    // 创建 presenter
    open fun initPresenter () {}

    // 加载数据
    open fun loadData () {}

    protected abstract fun getRootViewResId(): Int

}
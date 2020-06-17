package com.owulia.taobaounion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.owulia.taobaounion.R
import kotlinx.android.synthetic.main.base_fragment_layout.*

abstract class BaseFragment : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.base_fragment_layout, container, false)
        val containerView = rootView?.findViewById<ViewGroup>(R.id.baseContainer)
        loadStateView(inflater, containerView)
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

    private fun loadStateView  (
        inflater: LayoutInflater,
        container: ViewGroup?)  {
        val successView = loadSuccessView(inflater, container)
        container?.addView(successView)
    }

    private fun loadSuccessView (
        inflater: LayoutInflater,
        container: ViewGroup?): View {
        return inflater.inflate(getRootViewResId(), container, false)
    }

    // 初始化 view
    open fun initView (view: View?) {}

    // 释放资源 presenter
    open fun release () {}

    // 创建 presenter
    open fun initPresenter () {}

    // 加载数据
    open fun loadData () {}

    protected abstract fun getRootViewResId(): Int

}
package com.owulia.taobaounion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.owulia.taobaounion.R

abstract class BaseFragment : Fragment() {

    private var successView: View? = null
    private var loadingView: View? = null
    private var errorView: View? = null
    private var emptyView: View? = null
    private var rootView: View? = null

    enum class State {
        NONE, LOADING, SUCCESS, ERROR, EMPTY
    }

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

    private fun loadStateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        // 成功的 View
        successView = inflater.inflate(getRootViewResId(), container, false)
        container?.addView(successView)
        // loading View
        loadingView = inflater.inflate(R.layout.fragment_loading, container, false)
        container?.addView(loadingView)
        // 错误页面 View
        errorView = inflater.inflate(R.layout.fragment_error, container, false)
        container?.addView(errorView)
        // 空页面
        emptyView = inflater.inflate(R.layout.fragment_empty, container, false)
        setUpState()
    }

    fun setUpState (state: State = State.NONE) {
        successView?.visibility = if (state == State.SUCCESS) View.VISIBLE else View.GONE
        loadingView?.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
        errorView?.visibility = if (state == State.ERROR) View.VISIBLE else View.GONE
        emptyView?.visibility = if (state == State.EMPTY) View.VISIBLE else View.GONE
    }

    private fun loadLoadingView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    private fun loadSuccessView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        return inflater.inflate(getRootViewResId(), container, false)
    }

    // 初始化 view
    open fun initView(view: View?) {}

    // 释放资源 presenter
    open fun release() {}

    // 创建 presenter
    open fun initPresenter() {}

    // 加载数据
    open fun loadData() {}

    protected abstract fun getRootViewResId(): Int

}
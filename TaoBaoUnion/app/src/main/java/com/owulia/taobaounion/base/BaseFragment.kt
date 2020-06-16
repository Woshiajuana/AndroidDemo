package com.owulia.taobaounion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getRootViewResId(), container, false)
        initPresenter()
        loadData()
        return rootView
    }

    // 创建 presenter
    open fun initPresenter () {}

    // 加载数据
    open fun loadData () {}

    protected abstract fun getRootViewResId(): Int

}
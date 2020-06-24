package com.owulia.wowcool.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var vRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vRootView = inflater.inflate(getViewResourceId(), container, false)
        return vRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(vRootView!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        release()
    }

    /**
     * 返回 rootView id
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
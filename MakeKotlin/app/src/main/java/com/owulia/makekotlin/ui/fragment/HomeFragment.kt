package com.owulia.makekotlin.ui.fragment

import androidx.fragment.app.Fragment
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    companion object {
        val instant: HomeFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HomeFragment() }
    }

    override fun getContentViewResourceId(): Int = R.layout.fragment_home

}

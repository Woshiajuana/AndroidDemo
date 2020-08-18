package com.owulia.makekotlin.ui.fragment

import androidx.fragment.app.Fragment

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment() {

    companion object {
        val instant: MineFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MineFragment() }
    }

    override fun getContentViewResourceId(): Int = R.layout.fragment_mine

}

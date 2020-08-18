package com.owulia.makekotlin.ui.fragment

import androidx.fragment.app.Fragment
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class MaterialFragment : BaseFragment() {

    companion object {
        val instant: MaterialFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MaterialFragment() }
    }

    override fun getContentViewResourceId(): Int = R.layout.fragment_material

}

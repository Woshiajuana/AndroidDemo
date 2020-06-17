package com.owulia.taobaounion.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HomePagerFragment : BaseFragment () {

    override fun getRootViewResId() = R.layout.fragment_home_pager

    override fun initView(view: View?) {
        super.initView(view)
        setUpState(State.SUCCESS)
    }

}

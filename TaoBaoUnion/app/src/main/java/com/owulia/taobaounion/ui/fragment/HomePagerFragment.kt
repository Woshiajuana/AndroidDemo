package com.owulia.taobaounion.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.utils.Constants

/**
 * A simple [Fragment] subclass.
 */
class HomePagerFragment : BaseFragment () {

    companion object {
        val instant: (Categories.Data) -> HomePagerFragment = {
            val homePagerFragment = HomePagerFragment()
            val bundle = Bundle()
            bundle.putString(Constants.KEY_HOME_PAGER_TITLE, it.title)
            bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID, it.id)
            homePagerFragment.arguments = bundle
            homePagerFragment
        }
    }

    override fun getRootViewResId() = R.layout.fragment_home_pager

    override fun initView(view: View?) {
        super.initView(view)
        setUpState(State.SUCCESS)
    }

    override fun loadData() {
        super.loadData()
        val title = arguments?.getString(Constants.KEY_HOME_PAGER_TITLE)
        val materialId = arguments?.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID)
    }

}

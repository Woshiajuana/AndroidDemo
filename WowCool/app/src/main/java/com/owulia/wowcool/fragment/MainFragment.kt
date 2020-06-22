package com.owulia.wowcool.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.owulia.wowcool.R
import com.owulia.wowcool.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val mMainFragmentViewModel by viewModels<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        wtMainTabBar.apply {
            setItemIcon(resources.getDimension(R.dimen.dimen_tab_bar_icon).toInt())
            setItemText(
                ContextCompat.getColor(context, R.color.colorTabBarNormal),
                ContextCompat.getColor(context, R.color.colorTabBarActive)
            )
            addItem(
                R.drawable.ic_tabbar_cool_normal, R.drawable.ic_tabbar_cool_active,
                getString(R.string.string_tab_bar_home), HomeFragment()
            )
            addItem(
                R.drawable.ic_tabbar_demo_normal, R.drawable.ic_tabbar_demo_active,
                getString(R.string.string_tab_bar_demo), DemoFragment.instant
            )
            addItem(
                R.drawable.ic_tabbar_mine_normal, R.drawable.ic_tabbar_mine_active,
                getString(R.string.string_tab_bar_mine), MineFragment()
            )
            setDivider(true, 1)
            setOnItemClickListener = { index, _ ->
                mMainFragmentViewModel.mNumCurrent.value = index
                true
            }
            build(childFragmentManager)
            switchItem(mMainFragmentViewModel.mNumCurrent.value?:0)
        }

    }

}

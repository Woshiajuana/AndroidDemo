package com.owulia.wowcool.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.owulia.wowcool.ui.fragment.DemoFragment

class MainPagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return DemoFragment.instant
    }

    override fun getCount(): Int {
        return 1
    }
}
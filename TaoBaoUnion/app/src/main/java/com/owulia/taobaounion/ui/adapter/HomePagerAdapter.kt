package com.owulia.taobaounion.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.ui.fragment.HomePagerFragment

class HomePagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var categoryList: ArrayList<Categories.Data> = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryList[position].title
    }

    override fun getItem(position: Int): Fragment {
        return HomePagerFragment()
    }

    override fun getCount(): Int {
        return categoryList.size
    }

    fun setCategories(categories: Categories) {
        categoryList.clear()
        categoryList.addAll(categories.data)
        notifyDataSetChanged()
    }

}
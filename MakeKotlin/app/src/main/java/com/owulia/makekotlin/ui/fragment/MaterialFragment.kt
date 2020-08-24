package com.owulia.makekotlin.ui.fragment

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.makekotlin.R
import com.owulia.makekotlin.adapter.MaterialMenuAdapter
import com.owulia.makekotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_material.*

/**
 * A simple [Fragment] subclass.
 */
class MaterialFragment : BaseFragment() {

    companion object {
        val instant: MaterialFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MaterialFragment() }
    }

    override val mNavBarLeftImg: Int = -1

    override val isStatusBarLightMode: Boolean = false

    override val mNavBarTitle: Int = R.string.string_tab_bar_yl

    override val mNavBarRightImg: Int = R.mipmap.ic_white_message

    override fun getContentViewResourceId(): Int = R.layout.fragment_material

    override fun initView() {
        super.initView()
        vNavBar?.apply {
            setNavBarTitleLeftAlign()
            getVTitle().setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorMain))
        }
        vStatusBar?.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorMain))
        }
        render(RenderState.SUCCESS)

        vMenuBox.apply {
            layoutManager = GridLayoutManager(context, 4)
            isNestedScrollingEnabled = false
            adapter = MaterialMenuAdapter()
        }
    }

}

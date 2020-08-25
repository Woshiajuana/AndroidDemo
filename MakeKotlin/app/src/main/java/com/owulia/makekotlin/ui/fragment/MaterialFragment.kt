package com.owulia.makekotlin.ui.fragment

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.makekotlin.R
import com.owulia.makekotlin.adapter.MaterialMenuAdapter
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.model.MaterialMenuModel
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

    private val mArrMenu = arrayOf(
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_ls, text = R.string.string_material_menu_ls),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_yqmy, text = R.string.string_material_menu_yqmy),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_rmyq, text = R.string.string_material_menu_rmyq),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_kplus, text = R.string.string_material_menu_kplus),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_gdm, text = R.string.string_material_menu_gdm),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_umi, text = R.string.string_material_menu_umi),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_shgl, text = R.string.string_material_menu_shgl),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_shtz, text = R.string.string_material_menu_shtz),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_xykcp, text = R.string.string_material_menu_xykcp),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_tjbk, text = R.string.string_material_menu_tjbk),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_jfdh, text = R.string.string_material_menu_jfdh),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_kjdh, text = R.string.string_material_menu_kjdh),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_sc, text = R.string.string_material_menu_sc),
        MaterialMenuModel(icon = R.mipmap.ic_m_menu_cxwy, text = R.string.string_material_menu_cxwy)
    )

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
            adapter = MaterialMenuAdapter(mArrMenu)
        }
        initMenuView()
    }

    /**
     * 初始化菜单
     * */
    private fun initMenuView () {

    }

}

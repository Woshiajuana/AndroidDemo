package com.owulia.makekotlin.ui.fragment

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.model.MenuCellModel
import com.owulia.makekotlin.widget.MenuCellView
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment() {

    companion object {
        val instant: MineFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MineFragment() }
    }

    override val mNavBarLeftImg: Int = -1


    override val isStatusBarLightMode: Boolean = false

    override val mNavBarTitle: Int = R.string.string_tab_bar_mine

    override val mNavBarRightImg: Int = R.mipmap.ic_white_message

    override fun getContentViewResourceId(): Int = R.layout.fragment_mine

    /**
     * 菜单数据
     * */
    private val mArrMenu = arrayListOf<MenuCellModel>(
        MenuCellModel( labelText = R.string.string_mine_menu_smrz, prefixImg = R.mipmap.ic_mine_menu_smrz, setOnClickListener = {}),
        MenuCellModel( labelText = R.string.string_mine_menu_xykgl, prefixImg = R.mipmap.ic_mine_menu_xykgl, setOnClickListener = {}),
        MenuCellModel( labelText = R.string.string_mine_menu_czzn, prefixImg = R.mipmap.ic_mine_menu_czzn, setOnClickListener = {}),
        MenuCellModel( labelText = R.string.string_mine_menu_zskf, prefixImg = R.mipmap.ic_mine_menu_zskf, setOnClickListener = {}),
        MenuCellModel( labelText = R.string.string_mine_menu_gymk, prefixImg = R.mipmap.ic_mine_menu_gymk, setOnClickListener = {}),
        MenuCellModel( labelText = R.string.string_mine_menu_tysz, prefixImg = R.mipmap.ic_mine_menu_tysz, setOnClickListener = {})
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

        initMenuView()
    }

    /**
     * 添加菜单
     * */
    private fun initMenuView () {
        mArrMenu.forEachIndexed{ index, menuCellModel ->
            if (index < 2) {
                vMenuGroup.addView(MenuCellView(context!!, menuCellModel))
            } else {
                vMenuGroup1.addView(MenuCellView(context!!, menuCellModel))
            }
        }
    }
}

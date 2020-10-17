package com.owulia.makekotlin.ui.fragment

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.bean.MenuCellBean
import com.owulia.makekotlin.ui.activity.GuidePageActivity
import com.owulia.makekotlin.ui.activity.UserAccountActivity
import com.owulia.makekotlin.ui.widget.MenuCellView
import com.owulia.makekotlin.utils.WowToastUtils
import com.owulia.makekotlin.widget.WowConfirmDialog
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.widget_confirm_dialog.*

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
    private val mArrMenu = arrayOf(
        MenuCellBean(
            labelText = R.string.string_mine_menu_smrz,
            prefixImg = R.mipmap.ic_mine_menu_smrz,
            setOnClickListener = {}),
        MenuCellBean(
            labelText = R.string.string_mine_menu_xykgl,
            prefixImg = R.mipmap.ic_mine_menu_xykgl,
            setOnClickListener = {}),
        MenuCellBean(
            labelText = R.string.string_mine_menu_czzn,
            prefixImg = R.mipmap.ic_mine_menu_czzn,
            setOnClickListener = {}),
        MenuCellBean(
            labelText = R.string.string_mine_menu_zskf,
            prefixImg = R.mipmap.ic_mine_menu_zskf,
            setOnClickListener = {}),
        MenuCellBean(
            labelText = R.string.string_mine_menu_gymk,
            prefixImg = R.mipmap.ic_mine_menu_gymk,
            setOnClickListener = {}),
        MenuCellBean(
            labelText = R.string.string_mine_menu_tysz,
            prefixImg = R.mipmap.ic_mine_menu_tysz,
            setOnClickListener = {})
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
                vMenuGroup.addView(
                    MenuCellView(
                        context!!,
                        menuCellModel
                    )
                )
            } else {
                vMenuGroup1.addView(
                    MenuCellView(
                        context!!,
                        menuCellModel
                    )
                )
            }
        }
    }

    override fun initListener() {
        super.initListener()

        vNavBar?.setOnRightBtnClickListener = {
            WowConfirmDialog(requireContext()).apply {
                show()
                vTitle.setText(R.string.string_confirm_common_title)
                vMessage.setText(R.string.string_user_logout_tip)
                setSureOnClickListener = {
                    hide()
                    this@MineFragment.requireActivity().overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                    startActivity(Intent(this@MineFragment.requireContext(), UserAccountActivity::class.java))
                    requireActivity().finish()
                    false
                }
            }
        }
    }
}

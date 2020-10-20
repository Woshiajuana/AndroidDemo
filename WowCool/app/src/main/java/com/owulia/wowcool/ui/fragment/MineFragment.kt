package com.owulia.wowcool.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.wowcool.R
import com.owulia.wowcool.base.BaseFragment
import com.owulia.wowcool.utils.ConstantsUtils
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment() {

    companion object {
        val instant: MineFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { MineFragment() }
    }

    override val mNavBarTitle: Int = R.string.string_tab_bar_mine
    override val mNavBarLeftImage: Int = ConstantsUtils.NAV_BAR_LEFT_IMAGE_NUM_NULL

    override fun getViewResourceId(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        super.initView(view)
        setNavBarTitleLeftAlign()
    }

    override fun initEvent() {
        super.initEvent()
        vSubmitButton.setOnClickListener {
            val keyword = vKeywordInput.text.toString()
            val bundle = Bundle().apply {
                putString(ConstantsUtils.WEB_VIEW_TITLE, keyword)
                putString(ConstantsUtils.WEB_VIEW_URL, "https://so.m.sm.cn/api/rest?uc_param_str=dnntnwvepffrgibijbprsvdsmelood&dn=37369803699-46f4a6f6&nt=2&nw=WIFI&ve=13.1.0.1090&pf=145&fr=android&gi=bTkwBOfo3RjBMz1H7kR+ecNLa/MXwFV1CaRdJiffHWwljA==&bi=34464&jb=0&pr=UCMobile&sv=ucrelease1d&ds=bTkwBNA+On3B0ZOwpKPF5LR8YNYMHr9MndzGLq7deM7rhQ==&me=AATjwjTGKUHlhgTE2iStKTVK&lo=AAQRAtQtd9XY54gmHPf775eHsrgTpUH/HkgbuY6PkXzNmIo/YWyVZlu8HaCnk+iW7e3RPJVT+AtfvVWQDXBVtBOz75YfaWQW17uzG2j6bjipJ59uGZgO1vHArJtl6+bxyer86mR1XQJ2NJhGdiSNclnb&from=wh10331&method=Picbig.index&format=html&schema=v2&query=${keyword}&source=sc&scname=life_show_general_image")
            }
            routerPush(R.id.action_mainFragment_to_webViewFragment, bundle)
        }
    }

}

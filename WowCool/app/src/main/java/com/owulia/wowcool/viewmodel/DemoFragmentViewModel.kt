package com.owulia.wowcool.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.owulia.wowcool.R
import com.owulia.wowcool.bean.DemoItemBean

class DemoFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var arrDemo: MutableLiveData<List<DemoItemBean>> = MutableLiveData<List<DemoItemBean>> (
        mutableListOf (
            DemoItemBean(
                application.resources.getString(R.string.string_demo_tabbar_icon),
                application.resources.getString(R.string.string_demo_tabbar_text)
            ),
            DemoItemBean(
                application.resources.getString(R.string.string_demo_iconfont_icon),
                application.resources.getString(R.string.string_demo_iconfont_text)
            ),
            DemoItemBean(
                application.resources.getString(R.string.string_demo_wh_icon),
                application.resources.getString(R.string.string_demo_wh_text)
            ),
            DemoItemBean(
                application.resources.getString(R.string.string_demo_slide_menu_icon),
                application.resources.getString(R.string.string_demo_slide_menu_text),
                R.id.action_mainFragment_to_demoSlideMenuFragment
            )
        )
    )
}
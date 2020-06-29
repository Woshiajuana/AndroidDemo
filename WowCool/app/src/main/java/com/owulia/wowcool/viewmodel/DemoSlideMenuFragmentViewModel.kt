package com.owulia.wowcool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owulia.wowcool.bean.DemoSlideMenuItem

class DemoSlideMenuFragmentViewModel : ViewModel() {
    var arrData = MutableLiveData<List<DemoSlideMenuItem>> (
        mutableListOf(
            DemoSlideMenuItem("第一条数据"),
            DemoSlideMenuItem("第二条数据"),
            DemoSlideMenuItem("第三条数据"),
            DemoSlideMenuItem("第四条数据"),
            DemoSlideMenuItem("第五条数据"),
            DemoSlideMenuItem("第六条数据"),
            DemoSlideMenuItem("第七条数据"),
            DemoSlideMenuItem("第八条数据"),
            DemoSlideMenuItem("第九条数据"),
            DemoSlideMenuItem("第十条数据"),
            DemoSlideMenuItem("第十一条数据"),
            DemoSlideMenuItem("第十二条数据"),
            DemoSlideMenuItem("第十三条数据"),
            DemoSlideMenuItem("第十四条数据"),
            DemoSlideMenuItem("第十五条数据"),
            DemoSlideMenuItem("第十六条数据"),
            DemoSlideMenuItem("第十七条数据"),
            DemoSlideMenuItem("第十八条数据"),
            DemoSlideMenuItem("第十九条数据"),
            DemoSlideMenuItem("第二十条数据")
        )
    )
}
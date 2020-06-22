package com.owulia.wowcool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owulia.wowcool.bean.DemoItemBean

class DemoViewModel : ViewModel () {
    var arrDemo: MutableLiveData<List<DemoItemBean>> = MutableLiveData<List<DemoItemBean>> ()
}
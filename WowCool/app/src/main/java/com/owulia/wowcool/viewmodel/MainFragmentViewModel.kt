package com.owulia.wowcool.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel : ViewModel () {

    val mNumCurrent: MutableLiveData<Int> = MutableLiveData(1)

}
package com.owulia.mvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owulia.mvvmdemo.repository.OnSellRepository

class OnSellViewModel : ViewModel() {

    private val onSellRepository by lazy {
        OnSellRepository()
    }

    /**
     * 加载更多内容
     * */
    fun loaderMore () {
        
    }

    /**
     * 加载首页内容
     * */
    fun loadContent () {

    }

    val contentList = MutableLiveData<MutableList<>>()

}
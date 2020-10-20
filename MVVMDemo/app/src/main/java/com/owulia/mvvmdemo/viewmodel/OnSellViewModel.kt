package com.owulia.mvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owulia.mvvmdemo.domain.OnSellData
import com.owulia.mvvmdemo.repository.OnSellRepository
import kotlinx.coroutines.launch

class OnSellViewModel : ViewModel() {

    val contentList = MutableLiveData<List<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>>()

    private val onSellRepository by lazy {
        OnSellRepository()
    }

    companion object {
        // 默认第一页
        const val DEFAULT_PAGE = 1
    }

    // 当前页
    private var mCurrentPage = DEFAULT_PAGE

    /**
     * 加载更多内容
     * */
    fun loaderMore () {
        // 加载更多
        mCurrentPage++
        listContentByPage(mCurrentPage)
    }

    /**
     * 加载首页内容
     * */
    fun loadContent () {
        listContentByPage(mCurrentPage)
    }

    private fun listContentByPage (page: Int) {
        viewModelScope.launch {
            val onSellList = onSellRepository.getOnSellList(page)
            contentList.value = onSellList.tbk_dg_optimus_material_response.result_list.map_data
        }
    }


}
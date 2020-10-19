package com.owulia.mvvmdemo.repository

import com.owulia.mvvmdemo.api.RetrofitClient

class OnSellRepository {

    suspend fun getOnSellList (page: Int) = RetrofitClient.apiService.getOnSellList(page).apiData()

}
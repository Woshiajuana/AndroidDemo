package com.owulia.taobaounion.model

import com.owulia.taobaounion.model.domain.Categories
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("discovery/categories")
    fun getCategories() : Call<Categories>

}
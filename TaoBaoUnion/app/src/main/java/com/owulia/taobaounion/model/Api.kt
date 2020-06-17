package com.owulia.taobaounion.model

import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.model.domain.HomePagerContent
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("discovery/categories")
    fun getCategories() : Call<Categories>

    @GET
    fun getContentByCategoryId(@Url url: String) : Call<HomePagerContent>

}
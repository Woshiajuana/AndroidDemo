package com.owulia.taobaounion.model

import com.owulia.taobaounion.model.domain.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("discovery/categories")
    fun getCategories() : Call<Categories>

    @GET
    fun getContentByCategoryId(@Url url: String) : Call<HomePagerContent>

    @POST("tpwd")
    fun getTicket(@Body ticketParams: TicketParams) :  Call<TicketResult>

    @GET("recommend/categories")
    fun getSelectedPageCategories() : Call<SelectedPageCategory>

    @GET
    fun getSelectedPageContent(@Url url: String) : Call<SelectedPageContent>



}
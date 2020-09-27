package com.owulia.makekotlin.utils

import retrofit2.Retrofit

class RetrofitManager private constructor() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)

}
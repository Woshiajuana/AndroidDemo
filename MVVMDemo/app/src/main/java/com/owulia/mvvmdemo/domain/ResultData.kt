package com.owulia.mvvmdemo.domain

import com.owulia.mvvmdemo.api.ApiException

data class ResultData<T>(
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: T
) {

    companion object {
        const val CODE_SUCCESS = 10000
    }

    fun apiData () : T {
        if (code == CODE_SUCCESS) {
            return data
        } else {
            throw ApiException(code, message)
        }
    }

}
package com.owulia.makekotlin.bean

import com.owulia.makekotlin.utils.Constants

data class RespBean<T : Any>(
    val code: Int?,
    val `data`: T?,
    val msg: String?
) {
    fun isOk () : Boolean {
        return code == Constants.CODE_SUCCESS
    }
}
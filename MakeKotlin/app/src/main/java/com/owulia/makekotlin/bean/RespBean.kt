package com.owulia.makekotlin.bean

data class RespBean<T>(
    val code: Int?,
    val `data`: T?,
    val msg: String?
)
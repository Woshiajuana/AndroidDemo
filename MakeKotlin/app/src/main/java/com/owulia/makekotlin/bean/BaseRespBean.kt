package com.owulia.makekotlin.bean

data class BaseRespBean<T>(
    val code: Int?,
    val `data`: T?,
    val msg: String?
)
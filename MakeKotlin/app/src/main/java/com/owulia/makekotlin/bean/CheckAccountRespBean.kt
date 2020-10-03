package com.owulia.makekotlin.bean

data class CheckAccountRespBean <T> (
    val code: Int?,
    val `data`: T?,
    val msg: String?
)
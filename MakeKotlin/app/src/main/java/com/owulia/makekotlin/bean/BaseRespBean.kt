package com.owulia.makekotlin.bean

data class BaseRespBean(
    val charset: String?,
    val headPortrait: String?,
    val isRegister: String?,
    val respDate: String?,
    val respTime: String?,
    val signature: String?,
    val tenantId: String?,
    val traceId: String?,
    val txnCode: String?,
    val version: String?,
    val workId: String?
)
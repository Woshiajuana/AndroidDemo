package com.owulia.user.data.protocol

data class LoginReq (
    val mobile: String,
    val pwd: String,
    val pushId: String
)
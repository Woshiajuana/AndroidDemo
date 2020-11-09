package com.owulia.user.data.protocol

data class ForgetReq (
    val mobile: String,
    val verifyCode: String
)
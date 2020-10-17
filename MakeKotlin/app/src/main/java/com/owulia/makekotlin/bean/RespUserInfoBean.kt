package com.owulia.makekotlin.bean

import com.google.gson.annotations.SerializedName

data class RespUserInfoBean(
    @SerializedName("access_token")
    val accessToken: String?,
    val dept_id: Any?,
    val expires_in: Int?,
    val license: String?,
    val loginType: String?,
    val operatorno: String?,
    val os: Any?,
    val product_line: Any?,
    val pwdResetFlag: String?,
    val refresh_token: String?,
    val scope: String?,
    val tenant_id: String?,
    val token_type: String?,
    val user_id: Long?,
    val user_kind: Any?,
    val username: String?
)
package com.owulia.taobaounion.model.domain

data class TicketResult(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
){
    data class Data(
        val tbk_tpwd_create_response: TbkTpwdCreateResponse
    ) {
        data class TbkTpwdCreateResponse(
            val `data`: DataX,
            val request_id: String
        ) {
            data class DataX(
                val model: String
            )
        }
    }
}
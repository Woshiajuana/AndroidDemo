package com.owulia.base.rx

class BaseException (
    val status: Int,
    val msg: String
) : Throwable() {
}
package com.owulia.wowcool.utils

class TestUtils {

    fun t () {
        Singleton.instant

    }
}


class Singleton private constructor() {
    companion object {
        private var mInstant: Singleton? = null
        val instant: Singleton = mInstant?: synchronized(this) {
            mInstant?: Singleton().also { mInstant = it }
        }
    }
}



package com.owulia.taobaounion.utils

class PresenterManager private constructor() {

    companion object {
        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { PresenterManager() }
    }

    

}
package com.owulia.taobaounion.utils

class UrlUtil {

    companion object {

        fun createHomePagerUrl (materialId: Int, page: Int) = "discovery/$materialId/$page"
        fun getCoverPath(pictUrl: String) = "https:${pictUrl}"

    }
}
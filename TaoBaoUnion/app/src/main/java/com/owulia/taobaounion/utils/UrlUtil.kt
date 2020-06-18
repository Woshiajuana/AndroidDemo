package com.owulia.taobaounion.utils

class UrlUtil {

    companion object {

        fun createHomePagerUrl (materialId: Int, page: Int) = "discovery/$materialId/$page"
        fun getCoverPath(pictUrl: String, size: Int? = null) = if (size == null) "https:${pictUrl}" else "https:${pictUrl}_${size}x${size}.jpg"

    }
}
package com.owulia.makekotlin.utils

import java.text.SimpleDateFormat
import java.util.*

class WowCommonUtils {

    companion object {

        /**
         * 格式化时间
         * @param date [Date]
         * @param pattern [String]
         * @return [String]
         * */
        fun formatDate (date: Date = Date(), pattern: String = "yyyy-MM-dd HH:mm:ss") : String{
            return SimpleDateFormat(pattern, Locale.ENGLISH).format(date)
        }

    }

}
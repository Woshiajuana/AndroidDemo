package com.owulia.makekotlin.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class WowCommonUtils {

    companion object {

        /**
         * 格式化手机号
         * @param phone [String]
         * @param division [String]
         * @return [String]
         * */
        fun formatPhone (phone: String, division: String = " ") : String {
            return "${phone.substring(0,3)}${division}****${division}${phone.substring(7)}"
        }

        /**
         * 格式化时间
         * @param date [Date] 时间
         * @param pattern [String] 格式
         * @return [String]
         * */
        fun formatDate (date: Date = Date(), pattern: String = "yyyy-MM-dd HH:mm:ss") : String {
            return SimpleDateFormat(pattern, Locale.ENGLISH).format(date)
        }

        /**
         * 随机数
         * @param length [Int] 长度
         * @return [String]
         * */
        fun randomString (length: Int = 6) : String {
            val result = StringBuffer()
            for (i in 0 until length) {
                result.append(floor(Math.random() * 10).toInt())
            }
            return result.toString()
        }

    }

}
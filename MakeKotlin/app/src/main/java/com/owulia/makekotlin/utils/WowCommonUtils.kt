package com.owulia.makekotlin.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class WowCommonUtils {

    companion object {

        /**
         * 校验手机号
         * @param v [String]
         * @return [Boolean]
         * */
        fun checkIsPhone (v: String) : Boolean {
            return Regex("""^1\d{10}${'$'}""").matches(v)
        }

        /**
         * 校验身份证
         * @param v [String]
         * @return [Boolean]
         * */
        fun checkIsIDCard (v: String) : Boolean {
            return Regex("""^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}${'$'})|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])${'$'})${'$'}""").matches(v)
        }

        /**
         * 校验银行卡
         * @param v [String]
         * @return [Boolean]
         * */
        fun checkIsBankCard (v: String) : Boolean {
            return Regex("""^(\d{16}|\d{18}|\d{19})${'$'}""").matches(v)
        }

        /**
         * 校验邮箱
         * @param v [String]
         * @return [Boolean]
         * */
        fun checkIsEmail(v: String) : Boolean {
            return Regex("""^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*${'$'}""").matches(v)
        }

        /**
         * 校验金额
         * @param v [String]
         * @return [Boolean]
         * */
        fun checkIsMoney(v: String) : Boolean {
            return Regex("""^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)${'$'}""").matches(v)
        }

        /**
         * 校验是否是中文
         * @param v [String]
         * @return [Boolean]
         * */
        fun checkIsZH(v: String) : Boolean {
            return Regex("""^[\d\w\u4e00-\u9fa5]{1,12}${'$'}""").matches(v)
        }

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
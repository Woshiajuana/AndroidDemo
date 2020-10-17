package com.owulia.makekotlin.utils

import cn.hutool.crypto.Mode
import cn.hutool.crypto.Padding
import cn.hutool.crypto.symmetric.AES
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AesUtils {

    /**
     * 加密
     * @param key [String]
     * @param content [String]
     * @return [String]
     * */
    fun encrypt(key: String, content: String): String {
        return AES(
            Mode.CBC,
            Padding.PKCS5Padding,
            SecretKeySpec(key.toByteArray(), "AES"),
            IvParameterSpec(key.toByteArray())
        ).encryptBase64(content)
    }

    /**
     * 解密
     * @param key [String]
     * @param content [String]
     * @return [String]
     * */
    fun decrypt(key: String, content: String): String {
        return AES(
            Mode.CBC,
            Padding.PKCS5Padding,
            SecretKeySpec(key.toByteArray(), "AES"),
            IvParameterSpec(key.toByteArray())
        ).decryptStr(content)
    }

}
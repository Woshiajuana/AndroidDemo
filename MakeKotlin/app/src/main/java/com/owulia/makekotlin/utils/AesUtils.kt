package com.owulia.makekotlin.utils

import cn.hutool.core.codec.Base64
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

object AesUtils {
    const val VIPARA = "1234567890123456"
    const val bm = "UTF-8"

    /**
     * 字节数组转化为大写16进制字符串
     *
     * @param b
     * @return
     */
    private fun byte2HexStr(b: ByteArray): String {
        val sb = StringBuilder()
        for (i in b.indices) {
            val s = Integer.toHexString((b[i] and 0xFF.toByte()).toInt())
            if (s.length == 1) {
                sb.append("0")
            }
            sb.append(s.toUpperCase())
        }
        return sb.toString()
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param s
     * @return
     */
    private fun str2ByteArray(s: String): ByteArray {
        val byteArrayLength = s.length / 2
        val b = ByteArray(byteArrayLength)
        for (i in 0 until byteArrayLength) {
            val b0 = Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16).toByte()
            b[i] = b0
        }
        return b
    }

    /**
     * AES 加密
     *
     * @param content  明文
     * @param
     * @return
     */
    fun aesEncrypt(content: String, password: String): String? {
        try {
            val zeroIv = IvParameterSpec(VIPARA.toByteArray())
            val key = SecretKeySpec(password.toByteArray(), "AES")
            val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv)
            val encryptedData: ByteArray =
                cipher.doFinal(content.toByteArray(charset(bm)))
            // return new String(encryptedData,bm);
            return Base64.encode(encryptedData)
            //          return byte2HexStr(encryptedData);
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * AES 解密
     *
     * @param content  密文
     * @param password 生成秘钥的关键字
     * @return
     */
    fun aesDecrypt(content: String?, password: String): String? {
        try {
            val byteMi: ByteArray = Base64.decode(content)
            //          byte[] byteMi=  str2ByteArray(content);
            val zeroIv = IvParameterSpec(VIPARA.toByteArray())
            val key = SecretKeySpec(password.toByteArray(), "AES")
            val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv)
            val decryptedData: ByteArray = cipher.doFinal(byteMi)
            return String(decryptedData)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }
        return null
    }
}
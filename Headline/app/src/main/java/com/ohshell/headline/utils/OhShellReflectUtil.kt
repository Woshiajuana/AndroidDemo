package com.ohshell.headline.utils

import android.view.LayoutInflater
import java.lang.reflect.ParameterizedType

object OhShellReflectUtil {
    /**
     * 创建 view binding
     * */
    fun<VB> createViewBinding(layoutInflater: LayoutInflater, clazz: Class<*>) : VB {
        return try {
            // 获取泛型参数对象
            val type: ParameterizedType = try {
                clazz.genericSuperclass as ParameterizedType
            } catch (e: java.lang.ClassCastException) {
                clazz.superclass.genericSuperclass as ParameterizedType
            }
            val clazzVB = type.actualTypeArguments[0] as Class<*>

            // 获取 inflate 方法
            val inflateMethod = clazzVB.getMethod("inflate", LayoutInflater::class.java)
            inflateMethod.invoke(null, layoutInflater) as VB
        } catch (e: Exception) {
            e.printStackTrace()
            throw java.lang.RuntimeException(e)
        }
    }
}
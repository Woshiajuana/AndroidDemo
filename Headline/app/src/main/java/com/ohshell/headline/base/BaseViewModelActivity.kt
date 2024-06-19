package com.ohshell.headline.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.ohshell.headline.utils.OhShellReflectUtil

open class BaseViewModelActivity<VB: ViewBinding> : BaseLogicActivity() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 调用 inflate 方法，创建 viewBinding
        binding = OhShellReflectUtil.createViewBinding(layoutInflater, javaClass)

        setContentView(binding.root)
    }
}
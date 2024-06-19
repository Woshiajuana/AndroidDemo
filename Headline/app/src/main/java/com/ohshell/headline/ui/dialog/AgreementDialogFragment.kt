package com.ohshell.headline.ui.dialog

import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentManager
import com.ohshell.headline.R
import com.ohshell.headline.base.BaseViewModelDialogFragment
import com.ohshell.headline.databinding.FragmentDialogAgreementBinding
import com.ohshell.headline.utils.OhShellProcessUtil
import com.ohshell.headline.utils.OhShellScreenUtil
import com.ohshell.headline.utils.OhShellTextUtil


class AgreementDialogFragment : BaseViewModelDialogFragment<FragmentDialogAgreementBinding>() {

    private lateinit var onAgreementClickListener: View.OnClickListener

    override fun initView() {
        super.initView()
        // 点击弹窗外边不能关闭
        isCancelable = false

        // 修改 Dialog 的宽度和高度
        val params = dialog!!.window!!.attributes
        params.width = (OhShellScreenUtil.getScreenWidth(requireContext()) * 0.9).toInt()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params

        OhShellTextUtil.setLinkColor(binding.content,  getColor(requireContext(), R.color.link))
    }

    override fun initData() {
        super.initData()
        binding.content.text = Html.fromHtml(getString(R.string.agreement_dialog_content))
    }

    override fun initListeners() {
        super.initListeners()
        binding.confirm.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }
        binding.cancel.setOnClickListener {
            dismiss()
            OhShellProcessUtil.killApp()
        }
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: View.OnClickListener) {
            val dialogFragment = AgreementDialogFragment()
            dialogFragment.onAgreementClickListener = onAgreementClickListener
            dialogFragment.show(fragmentManager, "AgreementDialogFragment")
        }
    }
}
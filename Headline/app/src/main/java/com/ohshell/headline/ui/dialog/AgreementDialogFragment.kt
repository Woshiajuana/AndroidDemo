package com.ohshell.headline.ui.dialog

import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.FragmentManager
import com.ohshell.headline.R
import com.ohshell.headline.base.BaseDialogFragment
import com.ohshell.headline.utils.OhShellProcessUtil
import com.ohshell.headline.utils.OhShellScreenUtil
import com.ohshell.headline.utils.OhShellTextUtil


class AgreementDialogFragment : BaseDialogFragment() {

    private lateinit var onAgreementClickListener: View.OnClickListener
    private lateinit var contentView: TextView
    private lateinit var btnConfirm: Button
    private lateinit var btnCancel: Button

    override fun initView() {
        super.initView()
        // 点击弹窗外边不能关闭
        isCancelable = false

        // 修改 Dialog 的宽度和高度
        val params = dialog!!.window!!.attributes
        params.width = (OhShellScreenUtil.getScreenWidth(requireContext()) * 0.9).toInt()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params

        contentView = findViewById(R.id.content)
        OhShellTextUtil.setLinkColor(contentView,  getColor(requireContext(), R.color.link))

        btnConfirm = findViewById(R.id.confirm)
        btnCancel = findViewById(R.id.cancel)
    }

    override fun initData() {
        super.initData()
        val content = Html.fromHtml(getString(R.string.agreement_dialog_content))
        contentView.text = content
    }

    override fun initListeners() {
        super.initListeners()
        btnConfirm.setOnClickListener {
            dismiss()
            onAgreementClickListener.onClick(it)
        }
        btnCancel.setOnClickListener {
            dismiss()
            OhShellProcessUtil.killApp()
        }
    }

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_agreement, container, false)
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onAgreementClickListener: View.OnClickListener) {
            val dialogFragment = AgreementDialogFragment()
            dialogFragment.onAgreementClickListener = onAgreementClickListener
            dialogFragment.show(fragmentManager, "AgreementDialogFragment")
        }
    }


}
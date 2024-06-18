package com.ohshell.headline.ui.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import com.ohshell.headline.R
import com.ohshell.headline.base.BaseDialogFragment
import com.ohshell.headline.utils.OhShellScreenUtil


class AgreementDialogFragment : BaseDialogFragment() {

    override fun initView() {
        super.initView()
        // 点击弹窗外边不能关闭
        isCancelable = false

        // 修改 Dialog 的宽度和高度
        val params = dialog!!.window!!.attributes
        params.width = (OhShellScreenUtil.getScreenWidth(requireContext()) * 0.9).toInt()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_agreement, container, false)
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            val dialogFragment = AgreementDialogFragment()
            dialogFragment.show(fragmentManager, "AgreementDialogFragment")
        }
    }


}
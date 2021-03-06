package com.owulia.wowcool.ui.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owulia.wowcool.R
import com.owulia.wowcool.utils.WowLinearSpacesItemDecorationHelper
import kotlinx.android.synthetic.main.dialog_web_view.*
import kotlinx.android.synthetic.main.item_dialog_web_view_cell.view.*

class WowWebViewDialog(context: Context) : Dialog(context, R.style.WowDialog) {

    private var vRootView: View? = null
    private var mWebExtendAdapter: WowWebViewDialogAdapter? = null
    private var mWebOperateAdapter: WowWebViewDialogAdapter? = null
    private var mExtendData = ArrayList<OperateItemBean>()
    private var mOperateData = ArrayList<OperateItemBean>()
    private var mTitleText = ""
    var setOnItemClickListener: ((OperateItemBean) -> Unit)? = null

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vRootView = LayoutInflater.from(context).inflate(R.layout.dialog_web_view, null)
        setContentView(vRootView!!)
        initView()
        initEvent()
    }

    private fun initView () {
        // 设置弹窗宽度
        vRootView?.layoutParams?.apply {
            width = context.resources.displayMetrics.widthPixels
        }
        window?.apply {
            // 设置弹窗内容位置
            setGravity(Gravity.BOTTOM)
            // 设置弹窗动画
            setWindowAnimations(R.style.Dialog_Bottom_Animation)
        }
        vDialogWebTitle.text = mTitleText
        // 设置 适配器
        vDialogWebExtend.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            mWebExtendAdapter = WowWebViewDialogAdapter().apply {
                setData(mExtendData)
            }
            adapter = mWebExtendAdapter
            addItemDecoration(WowLinearSpacesItemDecorationHelper(leftSpace = 20, rightSpace = 20, childCount = mExtendData.size))
        }
        vDialogWebOperate.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            mWebOperateAdapter = WowWebViewDialogAdapter().apply {
                setData(mOperateData)
            }
            adapter = mWebOperateAdapter
            addItemDecoration(WowLinearSpacesItemDecorationHelper(leftSpace = 20, rightSpace = 20, childCount = mOperateData.size))
        }
    }

    private fun initEvent () {
        vDialogWebCancel.setOnClickListener {
            dismiss()
        }
        mWebExtendAdapter?.setOnItemClickListener = setOnItemClickListener
        mWebOperateAdapter?.setOnItemClickListener = setOnItemClickListener
    }

    fun setTitle (title: String) {
        mTitleText = title
        vDialogWebTitle?.text = title
    }

    fun setExtendData (data: List<OperateItemBean>) {
        mExtendData.clear()
        mExtendData.addAll(data)
        vDialogWebExtend?.addItemDecoration(WowLinearSpacesItemDecorationHelper(leftSpace = 20, rightSpace = 20, childCount = data.size))
        mWebExtendAdapter?.setData(data)
    }

    fun setOperateData (data: List<OperateItemBean>) {
        mOperateData.clear()
        mOperateData.addAll(data)
        vDialogWebOperate?.addItemDecoration(WowLinearSpacesItemDecorationHelper(leftSpace = 20, rightSpace = 20, childCount = data.size))
        mWebOperateAdapter?.setData(data)
    }

    data class OperateItemBean (val icon: Int, val text: String, val key: String? = null)
}

class WowWebViewDialogAdapter : RecyclerView.Adapter<WowWebViewDialogAdapter.WowWebViewDialogViewHolder>() {
    class WowWebViewDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val mArrData = ArrayList<WowWebViewDialog.OperateItemBean>()
    var setOnItemClickListener: ((WowWebViewDialog.OperateItemBean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WowWebViewDialogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_web_view_cell, parent, false)
        return WowWebViewDialogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mArrData.size
    }

    override fun onBindViewHolder(holder: WowWebViewDialogViewHolder, position: Int) {
        val itemData = mArrData[position]
        holder.itemView.apply {
            setOnClickListener{view ->
                setOnItemClickListener?.let {
                    it(itemData)
                }
            }
            vItemText.text = itemData.text
            vItemIcon.setImageResource(itemData.icon)
        }
    }

    fun setData (data: List<WowWebViewDialog.OperateItemBean>) {
        mArrData.clear()
        mArrData.addAll(data)
        notifyDataSetChanged()
    }
}
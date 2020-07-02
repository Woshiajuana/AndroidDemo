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
import kotlinx.android.synthetic.main.dialog_web_view.*

class WowWebViewDialog(context: Context) : Dialog(context, R.style.WowDialog) {

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_web_view, null)
        setContentView(view)
        view.layoutParams.apply {
            width = context.resources.displayMetrics.widthPixels
        }
        window?.apply {
            setGravity(Gravity.BOTTOM)
            setWindowAnimations(R.style.Dialog_Bottom_Animation)
        }
        vDialogWebExtend.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = WowWebViewDialogAdapter()
            addItemDecoration(object : RecyclerView.ItemDecoration() {

            })
        }
        vDialogWebOperate.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = WowWebViewDialogAdapter()
        }
    }

    private fun initView () {

    }
}

class WowWebViewDialogAdapter : RecyclerView.Adapter<WowWebViewDialogAdapter.WowWebViewDialogViewHolder>() {
    class WowWebViewDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WowWebViewDialogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_web_view_cell, parent, false)
        return WowWebViewDialogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: WowWebViewDialogViewHolder, position: Int) {
//        TODO("Not yet implemented")
    }
}
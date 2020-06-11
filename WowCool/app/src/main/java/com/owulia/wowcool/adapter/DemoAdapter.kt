package com.owulia.wowcool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.wowcool.R
import com.owulia.wowcool.bean.DemoItemBean

class DemoAdapter (private val list: MutableList<DemoItemBean>) : RecyclerView.Adapter<DemoAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.demo_card_cell, parent, false)

        return DemoAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DemoAdapterHolder, position: Int) {

    }

}

class DemoAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
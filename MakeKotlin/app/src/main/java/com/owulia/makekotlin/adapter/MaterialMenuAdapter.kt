package com.owulia.makekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.makekotlin.R

class MaterialMenuAdapter : RecyclerView.Adapter<MaterialMenuAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MaterialMenuAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_material_menu, parent, false)
        return MaterialMenuAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 14
    }

    override fun onBindViewHolder(holder: MaterialMenuAdapterViewHolder, position: Int) {
    }

}

class MaterialMenuAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

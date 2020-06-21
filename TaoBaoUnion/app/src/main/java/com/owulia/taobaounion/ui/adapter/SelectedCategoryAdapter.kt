package com.owulia.taobaounion.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.taobaounion.R
import com.owulia.taobaounion.model.domain.SelectedPageCategory

class SelectedCategoryAdapter : RecyclerView.Adapter<SelectedCategoryViewHolder> () {

    private var data =  ArrayList<SelectedPageCategory.Data> ()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_selected_category, parent, false)
        return SelectedCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SelectedCategoryViewHolder, position: Int) {
    }

    fun setData(categories: List<SelectedPageCategory.Data>) {
        data.clear()
        data.addAll(categories)
        notifyDataSetChanged()
    }
}

class SelectedCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
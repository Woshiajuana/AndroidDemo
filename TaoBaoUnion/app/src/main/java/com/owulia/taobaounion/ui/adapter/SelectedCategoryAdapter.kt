package com.owulia.taobaounion.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.taobaounion.R
import com.owulia.taobaounion.model.domain.SelectedPageCategory
import kotlinx.android.synthetic.main.item_selected_category.view.*

class SelectedCategoryAdapter : RecyclerView.Adapter<SelectedCategoryViewHolder> () {

    private var data =  ArrayList<SelectedPageCategory.Data> ()
    var onItemClickListener : ((SelectedPageCategory.Data) -> Unit)? = null
    private var mCurrent = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_selected_category, parent, false)
        return SelectedCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SelectedCategoryViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.vCategoryText.text = item.favorites_title
        if (mCurrent == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#eeeeee"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))
        }
        holder.itemView.setOnClickListener {
//            if (onItemClickListener != null) onItemClickListener(item)
            onItemClickListener?.let { it(item) }
        }
    }

    fun setData(categories: List<SelectedPageCategory.Data>) {
        data.clear()
        data.addAll(categories)
        notifyDataSetChanged()
    }
}

class SelectedCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
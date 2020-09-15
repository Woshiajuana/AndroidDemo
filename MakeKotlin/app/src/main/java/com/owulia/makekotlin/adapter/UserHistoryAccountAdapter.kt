package com.owulia.makekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.makekotlin.R

class UserHistoryAccountAdapter : RecyclerView.Adapter<UserHistoryAccountAdapterViewHolder> () {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHistoryAccountAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_account, parent, false)
        return UserHistoryAccountAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: UserHistoryAccountAdapterViewHolder, position: Int) {

    }

}

class UserHistoryAccountAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

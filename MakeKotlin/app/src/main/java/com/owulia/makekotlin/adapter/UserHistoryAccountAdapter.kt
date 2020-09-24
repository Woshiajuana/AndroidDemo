package com.owulia.makekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.item_history_account.view.*

class UserHistoryAccountAdapter : RecyclerView.Adapter<UserHistoryAccountAdapterViewHolder> () {

    private var mArrData = ArrayList<String>()
    var setDeleteOnClickListener: ((Int) -> Boolean)? = null
    var setItemOnClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHistoryAccountAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_account, parent, false)
        return UserHistoryAccountAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mArrData.size
    }

    override fun onBindViewHolder(holder: UserHistoryAccountAdapterViewHolder, position: Int) {
        val account = mArrData[position]
        holder.itemView.apply {
            vTextAccount.apply {
                text = account
                setOnClickListener {
                    setItemOnClickListener?.let { it(account) }
                }
            }
            vImgDelete.setOnClickListener {
                setDeleteOnClickListener?.let {
                    if (it(position)) {
                        mArrData.removeAt(position)
                        notifyItemChanged(position)
                    }
                }
            }
        }
    }

    fun setData (data: ArrayList<String>) {
        mArrData.clear()
        mArrData.addAll(data)
        notifyDataSetChanged()
    }

}

class UserHistoryAccountAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

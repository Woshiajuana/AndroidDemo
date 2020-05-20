package com.owulia.imoocmusicdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.imoocmusicdemo.R

class MusicListAdapter (context: Context, parent: RecyclerView) : RecyclerView.Adapter<MusicListHolder>() {
    private val _context = context
    private val _parent = parent
    private lateinit var _itemView : View
    private var _isSetHeight = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListHolder {
        _itemView = LayoutInflater.from(_context).inflate(R.layout.item_list_music, parent, false)
        return MusicListHolder(_itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MusicListHolder, position: Int) {
        setRecyclerViewHeight()
    }

    private fun setRecyclerViewHeight () {
        if (_isSetHeight) return
        _isSetHeight = true
        val itemViewHeight = _itemView.layoutParams.height
        val recycleViewHeight = itemCount * itemViewHeight
        _parent.layoutParams.height = recycleViewHeight
    }
}

class MusicListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
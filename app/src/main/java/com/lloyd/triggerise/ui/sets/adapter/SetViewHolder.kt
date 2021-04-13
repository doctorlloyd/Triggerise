package com.lloyd.triggerise.ui.sets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lloyd.triggerise.data.models.sets.Set
import com.lloyd.triggerise.databinding.SetLayoutBinding
import com.lloyd.triggerise.ui.base.listeners.SetItemListener


class SetViewHolder(private val itemBinding: SetLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(set: Set, setItemListener: SetItemListener) {
        itemBinding.tvCaption.text = set.code
        itemBinding.tvName.text = set.name
        itemBinding.rlSetItem.setOnClickListener { setItemListener.onItemSelected(set) }
    }
}


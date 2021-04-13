package com.lloyd.triggerise.ui.sets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lloyd.triggerise.data.models.sets.Set
import androidx.recyclerview.widget.RecyclerView
import com.lloyd.triggerise.databinding.SetActivityBinding
import com.lloyd.triggerise.databinding.SetLayoutBinding
import com.lloyd.triggerise.ui.base.listeners.SetItemListener
import com.lloyd.triggerise.ui.sets.SetListViewModel

class SetAdapter(private val setListViewModel: SetListViewModel, private val set: List<Set>) : RecyclerView.Adapter<SetViewHolder>() {

    private val onItemClickListener: SetItemListener = object : SetItemListener {
        override fun onItemSelected(set: Set) {
            setListViewModel.openSetDetails(set)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val itemBinding = SetLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.bind(set[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return set.size
    }
}


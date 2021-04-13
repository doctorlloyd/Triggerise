package com.lloyd.triggerise.ui.cards.adapter

import androidx.recyclerview.widget.RecyclerView
import com.lloyd.triggerise.R
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.databinding.CardBinding
import com.lloyd.triggerise.ui.base.listeners.CardItemListener
import com.squareup.picasso.Picasso


class CardViewHolder(private val itemBinding: CardBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(card: Card, cardItemListener: CardItemListener) {
        itemBinding.tvCaption.text = card.name
        itemBinding.tvName.text = card.artist
        Picasso.get().load(card.imageUrl).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).into(itemBinding.ivCardItemImage)
        itemBinding.rlCardItem.setOnClickListener { cardItemListener.onItemSelected(card) }
    }
}


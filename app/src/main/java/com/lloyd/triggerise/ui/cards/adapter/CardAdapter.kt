package com.lloyd.triggerise.ui.cards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.databinding.CardBinding
import com.lloyd.triggerise.ui.base.listeners.CardItemListener
import com.lloyd.triggerise.ui.cards.CardListViewModel

class CardAdapter(private val cardListViewModel: CardListViewModel, private val card: List<Card>) : RecyclerView.Adapter<CardViewHolder>() {

    private val onItemClickListener: CardItemListener = object : CardItemListener {
        override fun onItemSelected(card: Card) {
            cardListViewModel.openCardDetails(card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemBinding = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(card[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return card.size
    }
}


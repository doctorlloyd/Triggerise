package com.lloyd.triggerise.ui.base.listeners

import com.lloyd.triggerise.data.models.cards.Card

interface CardItemListener {
    fun onItemSelected(card : Card)
}

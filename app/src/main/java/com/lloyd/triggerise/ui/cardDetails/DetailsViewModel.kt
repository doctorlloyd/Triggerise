package com.lloyd.triggerise.ui.cardDetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.ui.base.BaseViewModel
import com.lloyd.triggerise.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
open class DetailsViewModel @Inject constructor() : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val cardPrivate = MutableLiveData<Card>()
    val cardData: LiveData<Card> get() = cardPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openCardDetailsPrivate = MutableLiveData<SingleEvent<Card>>()
    val openCardDetails: LiveData<SingleEvent<Card>> get() = openCardDetailsPrivate

    fun initIntentData(card: Card) {
        cardPrivate.value = card
    }
}

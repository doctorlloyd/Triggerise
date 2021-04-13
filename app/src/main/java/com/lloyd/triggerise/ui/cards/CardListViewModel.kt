package com.lloyd.triggerise.ui.cards

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lloyd.triggerise.data.DataRepositorySource
import com.lloyd.triggerise.data.Resource
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.ui.base.BaseViewModel
import com.lloyd.triggerise.utils.SingleEvent
import com.lloyd.triggerise.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val cardsLiveDataPrivate = MutableLiveData<Resource<Cards>>()
    val cardsLiveData: LiveData<Resource<Cards>> get() = cardsLiveDataPrivate


    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openCardDetailsPrivate = MutableLiveData<SingleEvent<Card>>()
    val openCardDetails: LiveData<SingleEvent<Card>> get() = openCardDetailsPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getCards() {
        viewModelScope.launch {
            cardsLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestCards().collect {
                    cardsLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openCardDetails(card: Card) {
        openCardDetailsPrivate.value = SingleEvent(card)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}

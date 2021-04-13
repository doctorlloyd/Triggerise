package com.lloyd.triggerise.ui.setsDetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.data.models.sets.Set
import com.lloyd.triggerise.ui.base.BaseViewModel
import com.lloyd.triggerise.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
open class SetDetailsViewModel @Inject constructor() : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val setPrivate = MutableLiveData<Set>()
    val setData: LiveData<Set> get() = setPrivate

    fun initIntentData(set: Set) {
        setPrivate.value = set
    }
}

package com.lloyd.triggerise.ui.sets

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lloyd.triggerise.data.DataRepositorySource
import com.lloyd.triggerise.data.Resource
import com.lloyd.triggerise.data.models.sets.Set
import com.lloyd.triggerise.data.models.sets.Sets
import com.lloyd.triggerise.ui.base.BaseViewModel
import com.lloyd.triggerise.utils.SingleEvent
import com.lloyd.triggerise.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SetListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val setsLiveDataPrivate = MutableLiveData<Resource<Sets>>()
    val setsLiveData: LiveData<Resource<Sets>> get() = setsLiveDataPrivate


    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openSetDetailsPrivate = MutableLiveData<SingleEvent<Set>>()
    val openSetDetails: LiveData<SingleEvent<Set>> get() = openSetDetailsPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getSets() {
        viewModelScope.launch {
            setsLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestSets().collect {
                    setsLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openSetDetails(set: Set) {
        openSetDetailsPrivate.value = SingleEvent(set)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}

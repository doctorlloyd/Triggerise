package com.lloyd.triggerise.ui.base

import androidx.lifecycle.ViewModel
import com.lloyd.triggerise.errors.ErrorManager
import javax.inject.Inject


abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager
}

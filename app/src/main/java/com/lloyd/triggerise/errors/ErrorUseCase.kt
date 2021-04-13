package com.lloyd.triggerise.errors

import com.lloyd.triggerise.data.error.Error
interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}

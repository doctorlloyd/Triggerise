package com.lloyd.triggerise.errors

import com.lloyd.triggerise.data.error.mapper.ErrorMapper
import com.lloyd.triggerise.data.error.Error
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}

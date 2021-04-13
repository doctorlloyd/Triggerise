package com.lloyd.triggerise.di
import com.lloyd.triggerise.data.error.mapper.ErrorMapper
import com.lloyd.triggerise.data.error.mapper.ErrorMapperSource
import com.lloyd.triggerise.errors.ErrorManager
import com.lloyd.triggerise.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// with @Module we Telling Dagger that, this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}

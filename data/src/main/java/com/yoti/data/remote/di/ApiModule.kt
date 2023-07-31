package com.yoti.data.remote.di

import com.yoti.data.remote.api.CoinCapApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoinCapApiService {
        return retrofit.create(CoinCapApiService::class.java)
    }

}
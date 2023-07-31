package com.yoti.data.di

import com.yoti.data.repository.AssetRepositoryImpl
import com.yoti.data.repository.MarketRepositoryImpl
import com.yoti.domain.repository.AssetRepository
import com.yoti.domain.repository.MarketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAssetRepository(impl: AssetRepositoryImpl): AssetRepository

    @Binds
    @Singleton
    abstract fun bindsMarketRepository(impl: MarketRepositoryImpl): MarketRepository

}
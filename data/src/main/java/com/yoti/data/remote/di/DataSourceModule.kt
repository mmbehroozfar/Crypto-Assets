package com.yoti.data.remote.di

import com.yoti.data.remote.datasource.AssetRemoteDataSource
import com.yoti.data.remote.datasource.AssetRemoteDataSourceImpl
import com.yoti.data.remote.datasource.MarketRemoteDataSource
import com.yoti.data.remote.datasource.MarketRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsAssetRemoteDataSource(impl: AssetRemoteDataSourceImpl): AssetRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsMarketRemoteDataSource(impl: MarketRemoteDataSourceImpl): MarketRemoteDataSource

}
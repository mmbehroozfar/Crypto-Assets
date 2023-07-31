package com.yoti.data.local.di

import com.yoti.data.local.datasource.AssetLocalDataSource
import com.yoti.data.local.datasource.AssetLocalDataSourceImpl
import com.yoti.data.local.datasource.MarketLocalDataSource
import com.yoti.data.local.datasource.MarketLocalDataSourceImpl
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
    abstract fun bindsAssetLocalDataSource(impl: AssetLocalDataSourceImpl): AssetLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsMarketLocalDataSource(impl: MarketLocalDataSourceImpl): MarketLocalDataSource

}
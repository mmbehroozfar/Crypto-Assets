package com.yoti.data.local.di

import com.yoti.data.local.database.AssetRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideAssetDao(
        db: AssetRoomDatabase,
    ) = db.assetDao()

    @Provides
    @Singleton
    fun provideMarketDao(
        db: AssetRoomDatabase,
    ) = db.marketDao()

}
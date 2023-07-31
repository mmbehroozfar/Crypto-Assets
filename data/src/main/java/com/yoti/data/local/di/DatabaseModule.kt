package com.yoti.data.local.di

import android.content.Context
import androidx.room.Room
import com.yoti.data.local.database.AssetRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AssetRoomDatabase = Room
        .databaseBuilder(context, AssetRoomDatabase::class.java, "asset-db")
        .build()

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
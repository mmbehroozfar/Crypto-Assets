package com.yoti.android.cryptocurrencychallenge.di

import android.content.Context
import androidx.room.Room
import com.yoti.data.local.database.AssetRoomDatabase
import com.yoti.data.local.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
internal object InMemoryDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AssetRoomDatabase = Room.inMemoryDatabaseBuilder(context, AssetRoomDatabase::class.java)
        .allowMainThreadQueries()
        .build()

}
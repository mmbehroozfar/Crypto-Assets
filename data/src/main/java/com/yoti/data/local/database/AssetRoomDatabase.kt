package com.yoti.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoti.data.local.model.AssetEntity
import com.yoti.data.local.model.MarketEntity

@Database(
    entities = [
        AssetEntity::class,
        MarketEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AssetRoomDatabase : RoomDatabase(), AssetDatabase
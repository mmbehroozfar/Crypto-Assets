package com.yoti.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.yoti.data.local.model.MarketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<MarketEntity>)

    @Query("SELECT * FROM markets WHERE base_id = :assetId")
    fun getAssetMarkets(assetId: String): Flow<List<MarketEntity>>

}
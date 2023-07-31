package com.yoti.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.yoti.data.local.model.AssetEntity

@Dao
interface AssetDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<AssetEntity>)

    @Query("SELECT * FROM assets")
    fun observeAssetsPagedList(): PagingSource<Int, AssetEntity>

}
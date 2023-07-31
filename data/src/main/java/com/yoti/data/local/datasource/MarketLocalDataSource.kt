package com.yoti.data.local.datasource

import com.yoti.data.local.model.MarketEntity
import kotlinx.coroutines.flow.Flow

interface MarketLocalDataSource {

    suspend fun insert(entities: List<MarketEntity>)

    fun getAssetMarkets(assetId: String): Flow<List<MarketEntity>>

}
package com.yoti.domain.repository

import com.yoti.domain.model.Market
import kotlinx.coroutines.flow.Flow

interface MarketRepository {

    suspend fun updateMarketInformation(assetId: String)

    fun getMarketInformation(assetId: String): Flow<List<Market>>
}
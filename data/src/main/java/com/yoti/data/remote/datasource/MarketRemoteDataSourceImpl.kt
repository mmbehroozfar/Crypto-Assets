package com.yoti.data.remote.datasource

import com.yoti.data.remote.api.CoinCapApiService
import com.yoti.data.remote.util.bodyOrThrow
import javax.inject.Inject

class MarketRemoteDataSourceImpl @Inject constructor(
    private val apiService: CoinCapApiService,
) : MarketRemoteDataSource {

    override suspend fun getAssetMarkets(assetId: String) = bodyOrThrow {
        apiService.getAssetMarkets(assetId)
    }
}
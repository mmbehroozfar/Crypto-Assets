package com.yoti.data.remote.datasource

import com.yoti.data.remote.model.DataResponse
import com.yoti.data.remote.model.MarketResponse

interface MarketRemoteDataSource {

    suspend fun getAssetMarkets(assetId: String): DataResponse<MarketResponse>

}
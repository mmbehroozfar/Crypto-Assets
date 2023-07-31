package com.yoti.data.remote.api

import com.yoti.data.remote.model.AssetResponse
import com.yoti.data.remote.model.DataResponse
import com.yoti.data.remote.model.MarketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinCapApiService {

    @GET("v2/assets")
    suspend fun getAllAssets(): Response<DataResponse<AssetResponse>>

    @GET("v2/markets")
    suspend fun getAssetMarkets(@Query("baseId") assetId: String): Response<DataResponse<MarketResponse>>
}
package com.yoti.data.remote.datasource

import com.yoti.data.remote.api.CoinCapApiService
import com.yoti.data.remote.util.bodyOrThrow
import javax.inject.Inject

class AssetRemoteDataSourceImpl @Inject constructor(
    private val apiService: CoinCapApiService,
) : AssetRemoteDataSource {

    override suspend fun getAllAssets() = bodyOrThrow {
        apiService.getAllAssets()
    }

}
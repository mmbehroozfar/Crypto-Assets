package com.yoti.data.remote.datasource

import com.yoti.data.remote.model.AssetResponse
import com.yoti.data.remote.model.DataResponse

interface AssetRemoteDataSource {

    suspend fun getAllAssets(): DataResponse<AssetResponse>

}
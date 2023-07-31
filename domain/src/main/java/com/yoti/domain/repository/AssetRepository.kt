package com.yoti.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yoti.domain.model.Asset
import kotlinx.coroutines.flow.Flow

interface AssetRepository {

    suspend fun updateAssets()

    fun observePagesAssets(pagingConfig: PagingConfig): Flow<PagingData<Asset>>
}
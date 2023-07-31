package com.yoti.data.local.datasource

import androidx.paging.PagingSource
import com.yoti.data.local.model.AssetEntity

interface AssetLocalDataSource {

    suspend fun insert(entities: List<AssetEntity>)

    fun observeAssetsPagedList(): PagingSource<Int, AssetEntity>

}
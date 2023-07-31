package com.yoti.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.yoti.data.local.datasource.AssetLocalDataSource
import com.yoti.data.mapper.AssetMapper
import com.yoti.data.remote.datasource.AssetRemoteDataSource
import com.yoti.domain.extension.mapPagingData
import com.yoti.domain.repository.AssetRepository
import javax.inject.Inject

class AssetRepositoryImpl @Inject constructor(
    private val localDataSource: AssetLocalDataSource,
    private val remoteDataSource: AssetRemoteDataSource,
    private val assetMapper: AssetMapper,
) : AssetRepository {

    override suspend fun updateAssets() = remoteDataSource
        .getAllAssets()
        .data
        .map {
            assetMapper(it)
        }
        .let {
            localDataSource.insert(it)
        }

    override fun observePagesAssets(pagingConfig: PagingConfig) = Pager(
        config = pagingConfig,
        pagingSourceFactory = { localDataSource.observeAssetsPagedList() }
    ).flow.mapPagingData(assetMapper::invoke)

}
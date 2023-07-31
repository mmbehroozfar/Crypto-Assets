package com.yoti.data.repository

import com.yoti.data.local.datasource.MarketLocalDataSource
import com.yoti.data.mapper.MarketMapper
import com.yoti.data.remote.datasource.MarketRemoteDataSource
import com.yoti.domain.extension.mapList
import com.yoti.domain.repository.MarketRepository
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val localDataSource: MarketLocalDataSource,
    private val remoteDataSource: MarketRemoteDataSource,
    private val marketMapper: MarketMapper,
) : MarketRepository {

    override suspend fun updateMarketInformation(assetId: String) = remoteDataSource
        .getAssetMarkets(assetId).data
        .map {
            marketMapper(it)
        }
        .let {
            localDataSource.insert(it)
        }

    override fun getMarketInformation(assetId: String) = localDataSource
        .getAssetMarkets(assetId)
        .mapList { marketMapper(it) }


}
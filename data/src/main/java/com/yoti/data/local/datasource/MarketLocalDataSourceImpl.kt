package com.yoti.data.local.datasource

import com.yoti.data.local.dao.MarketDao
import com.yoti.data.local.model.MarketEntity
import javax.inject.Inject

class MarketLocalDataSourceImpl @Inject constructor(
    private val dao: MarketDao,
) : MarketLocalDataSource {

    override suspend fun insert(entities: List<MarketEntity>) = dao.insert(entities)

    override fun getAssetMarkets(assetId: String) = dao.getAssetMarkets(assetId)

}
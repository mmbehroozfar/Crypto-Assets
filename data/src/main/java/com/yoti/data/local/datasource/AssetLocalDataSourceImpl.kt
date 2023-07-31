package com.yoti.data.local.datasource

import com.yoti.data.local.dao.AssetDao
import com.yoti.data.local.model.AssetEntity
import javax.inject.Inject

class AssetLocalDataSourceImpl @Inject constructor(
    private val dao: AssetDao,
) : AssetLocalDataSource {

    override suspend fun insert(entities: List<AssetEntity>) = dao.insert(entities)

    override fun observeAssetsPagedList() = dao.observeAssetsPagedList()

}
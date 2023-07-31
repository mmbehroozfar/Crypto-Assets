package com.yoti.domain.usecase.asset

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yoti.domain.base.IoDispatcher
import com.yoti.domain.base.SubjectUseCase
import com.yoti.domain.model.Asset
import com.yoti.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ObservePagedAssetsUseCase @Inject constructor(
    private val repository: AssetRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : SubjectUseCase<Unit, PagingData<Asset>>(coroutineDispatcher) {

    private val pagingConfig = PagingConfig(
        pageSize = 15,
        enablePlaceholders = false,
    )

    override suspend fun createObservable(params: Unit): Flow<PagingData<Asset>> {
        return repository.observePagesAssets(pagingConfig)
    }

}
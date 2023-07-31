package com.yoti.domain.usecase.asset

import com.yoti.domain.base.IoDispatcher
import com.yoti.domain.base.ResultUseCase
import com.yoti.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class UpdateAssetsUseCase @Inject constructor(
    private val repository: AssetRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Unit) {
        return repository.updateAssets()
    }

}
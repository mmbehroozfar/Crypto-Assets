package com.yoti.domain.usecase.market

import com.yoti.domain.base.IoDispatcher
import com.yoti.domain.base.ResultUseCase
import com.yoti.domain.repository.MarketRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class UpdateMarketInformationUseCase @Inject constructor(
    private val repository: MarketRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<String, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: String) {
        return repository.updateMarketInformation(parameter)
    }

}
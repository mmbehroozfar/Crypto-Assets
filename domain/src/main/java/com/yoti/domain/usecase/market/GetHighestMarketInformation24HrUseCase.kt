package com.yoti.domain.usecase.market

import com.yoti.domain.base.FlowUseCase
import com.yoti.domain.model.Market
import com.yoti.domain.repository.MarketRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHighestMarketInformation24HrUseCase @Inject constructor(
    private val repository: MarketRepository,
) : FlowUseCase<String, Market?>() {

    override fun execute(parameter: String): Flow<Market?> {
        return repository.getMarketInformation(parameter)
            .map { markets ->
                markets.maxByOrNull {
                    it.volume24Hours.toDouble()
                }
            }
    }

}
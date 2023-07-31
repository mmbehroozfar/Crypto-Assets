package com.yoti.data.mapper

import com.yoti.data.local.model.MarketEntity
import com.yoti.data.remote.model.MarketResponse
import com.yoti.domain.model.Market
import javax.inject.Inject

class MarketMapper @Inject constructor() {

    operator fun invoke(type: MarketResponse) = MarketEntity(
        id = type.exchangeId + type.baseSymbol + type.quoteSymbol,
        baseId = type.baseId,
        baseSymbol = type.baseSymbol,
        exchangeId = type.exchangeId,
        percentExchangeVolume = type.percentExchangeVolume,
        priceQuote = type.priceQuote,
        priceUsd = type.priceUsd,
        quoteId = type.quoteId,
        quoteSymbol = type.quoteSymbol,
        rank = type.rank,
        tradesCount24Hr = type.tradesCount24Hr,
        updated = type.updated,
        volumeUsd24Hr = type.volumeUsd24Hr,
    )

    operator fun invoke(type: MarketEntity) = Market(
        symbol = type.baseSymbol,
        exchangeId = type.exchangeId,
        price = type.priceUsd,
        rank = type.rank,
        volume24Hours = type.volumeUsd24Hr,
        updated = type.updated,
    )
}
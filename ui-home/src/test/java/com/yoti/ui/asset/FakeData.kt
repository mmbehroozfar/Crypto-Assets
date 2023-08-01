package com.yoti.ui.asset

import com.yoti.domain.model.Asset
import com.yoti.domain.model.Market
import com.yoti.ui.asset.model.AssetUi
import com.yoti.ui.asset.model.MarketUi

object FakeData {
    val marketDomain = Market(
        symbol = "BTC",
        exchangeId = "binance",
        price = "30000",
        rank = "1",
        volume24Hours = "100",
        updated = 1690879255478,
    )
    val marketUi = MarketUi(
        symbol = "BTC",
        exchangeId = "binance",
        price = "30000",
        rank = "1",
        volume24Hours = "100",
        updated = "01/08/2023",
    )
    val assetDomain = Asset(
        id = "bitcoin",
        symbol = "BTC",
        name = "BitCoin",
        price = "30000",
    )
    val assetUi = AssetUi(
        id = "bitcoin",
        symbol = "BTC",
        name = "BitCoin",
        price = "30000",
    )
}
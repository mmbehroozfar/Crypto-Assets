package com.yoti.ui.asset.mapper

import com.yoti.domain.model.Market
import com.yoti.ui.asset.model.MarketUi
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MarketMapper @Inject constructor() {

    operator fun invoke(type: Market) = MarketUi(
        symbol = type.symbol,
        exchangeId = type.exchangeId,
        price = type.price ?: "",
        rank = type.rank,
        volume24Hours = type.volume24Hours ?: "",
        updated = type.updated.formatDate(),
    )

    private fun Long.formatDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return simpleDateFormat.format(this) ?: ""
    }
}


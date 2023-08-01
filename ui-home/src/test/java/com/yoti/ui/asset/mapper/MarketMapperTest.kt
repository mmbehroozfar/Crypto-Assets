package com.yoti.ui.asset.mapper

import com.google.common.truth.Truth
import com.yoti.ui.asset.FakeData
import org.junit.jupiter.api.Test

class MarketMapperTest {

    @Test
    fun mapDataCorrectly() {
        val mapper = MarketMapper()

        val result = mapper(FakeData.marketDomain)

        Truth.assertThat(result.symbol).isEqualTo(FakeData.marketUi.symbol)
        Truth.assertThat(result.exchangeId).isEqualTo(FakeData.marketUi.exchangeId)
        Truth.assertThat(result.price).isEqualTo(FakeData.marketUi.price)
        Truth.assertThat(result.rank).isEqualTo(FakeData.marketUi.rank)
        Truth.assertThat(result.volume24Hours).isEqualTo(FakeData.marketUi.volume24Hours)
        Truth.assertThat(result.updated).isEqualTo(FakeData.marketUi.updated)
    }
}
package com.yoti.ui.asset.mapper

import com.google.common.truth.Truth
import com.yoti.ui.asset.FakeData
import org.junit.jupiter.api.Test

class AssetMapperTest {

    @Test
    fun mapDataCorrectly() {
        val mapper = AssetMapper()

        val result = mapper(FakeData.assetDomain)

        Truth.assertThat(result.id).isEqualTo(FakeData.assetUi.id)
        Truth.assertThat(result.symbol).isEqualTo(FakeData.assetUi.symbol)
        Truth.assertThat(result.name).isEqualTo(FakeData.assetUi.name)
        Truth.assertThat(result.price).isEqualTo(FakeData.assetUi.price)
    }

}
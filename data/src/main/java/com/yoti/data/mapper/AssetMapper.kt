package com.yoti.data.mapper

import com.yoti.data.local.model.AssetEntity
import com.yoti.data.remote.model.AssetResponse
import com.yoti.domain.model.Asset
import javax.inject.Inject

class AssetMapper @Inject constructor() {

    operator fun invoke(type: AssetResponse) = AssetEntity(
        id = type.id,
        name = type.name,
        priceUsd = type.priceUsd,
        symbol = type.symbol,
    )

    operator fun invoke(type: AssetEntity) = Asset(
        id = type.id,
        name = type.name,
        price = type.priceUsd,
        symbol = type.symbol,
    )
}
package com.yoti.ui.asset.mapper

import com.yoti.domain.model.Asset
import com.yoti.ui.asset.model.AssetUi
import javax.inject.Inject

class AssetMapper @Inject constructor() {

    operator fun invoke(type: Asset) = AssetUi(
        id = type.id,
        symbol = type.symbol,
        name = type.name,
        price = type.price,
    )
}
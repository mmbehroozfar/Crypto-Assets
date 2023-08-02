package com.yoti.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetResponse(
    @SerialName("changePercent24Hr")
    val changePercent24Hr: String? = null,
    @SerialName("id")
    val id: String,
    @SerialName("marketCapUsd")
    val marketCapUsd: String,
    @SerialName("maxSupply")
    val maxSupply: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("priceUsd")
    val priceUsd: String,
    @SerialName("rank")
    val rank: String,
    @SerialName("supply")
    val supply: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("volumeUsd24Hr")
    val volumeUsd24Hr: String,
    @SerialName("vwap24Hr")
    val vwap24Hr: String? = null,
)
package com.yoti.domain.model

data class Market(
    val symbol: String,
    val exchangeId: String,
    val price: String,
    val rank: String,
    val volume24Hours: String,
    val updated: Long,
)
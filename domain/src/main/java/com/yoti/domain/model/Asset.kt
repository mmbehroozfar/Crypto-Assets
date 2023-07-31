package com.yoti.domain.model

data class Asset(
    val id: String,
    val changePercent24Hr: String,
    val symbol: String,
    val name: String,
    val price: String,
)
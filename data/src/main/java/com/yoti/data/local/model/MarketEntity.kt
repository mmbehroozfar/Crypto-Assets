package com.yoti.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "markets",
    indices = [
        Index(value = ["base_id"])
    ]
)
data class MarketEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("base_id")
    val baseId: String,
    @ColumnInfo("base_symbol")
    val baseSymbol: String,
    @ColumnInfo("exchange_id")
    val exchangeId: String,
    @ColumnInfo("percent_exchange_volume")
    val percentExchangeVolume: String? = null,
    @ColumnInfo("price_quote")
    val priceQuote: String,
    @ColumnInfo("price_usd")
    val priceUsd: String?,
    @ColumnInfo("quote_id")
    val quoteId: String,
    @ColumnInfo("quote_symbol")
    val quoteSymbol: String,
    @ColumnInfo("trades_count_24_hr")
    val tradesCount24Hr: String? = null,
    @ColumnInfo("volume_usd_24_hr")
    val volumeUsd24Hr: String? = null,
    val updated: Long,
    val rank: String,
)
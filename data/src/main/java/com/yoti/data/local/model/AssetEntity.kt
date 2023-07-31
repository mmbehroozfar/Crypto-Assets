package com.yoti.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class AssetEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("price_usd")
    val priceUsd: String,
    val name: String,
    val symbol: String,
)
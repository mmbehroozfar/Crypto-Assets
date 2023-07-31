package com.yoti.data.local.database

import com.yoti.data.local.dao.AssetDao
import com.yoti.data.local.dao.MarketDao

interface AssetDatabase {

    fun assetDao(): AssetDao

    fun marketDao(): MarketDao

}
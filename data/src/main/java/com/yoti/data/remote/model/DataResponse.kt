package com.yoti.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    @SerialName("data")
    val data: List<T>,
    @SerialName("timestamp")
    val timestamp: Long,
)
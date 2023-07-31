package com.yoti.domain.extension

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T : Any, R : Any> Flow<PagingData<T>>.mapPagingData(
    crossinline transform: suspend (T) -> R,
) = map { pagingData ->
    pagingData.map { data ->
        transform(data)
    }
}

inline fun <T : Any, R : Any> Flow<List<T>>.mapList(
    crossinline transform: suspend (T) -> R,
) = map { list ->
    list.map { data ->
        transform(data)
    }
}
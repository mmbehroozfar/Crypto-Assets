package com.yoti.ui.asset.mapper

import com.yoti.domain.error.NetworkError
import com.yoti.ui.home.R
import javax.inject.Inject

class ErrorMapper @Inject constructor() {

    operator fun invoke(exception: Exception) = when (exception as? NetworkError) {
        NetworkError.NoInternet -> R.string.internet_connection_is_unavailable
        NetworkError.Server -> R.string.it_s_not_you_it_s_us
        NetworkError.Timeout -> R.string.we_have_some_connection_issues
        else -> R.string.something_went_wrong
    }
}
package com.yoti.ui.asset.mapper

import com.google.common.truth.Truth
import com.yoti.domain.error.NetworkError
import com.yoti.ui.home.R
import org.junit.jupiter.api.Test

class ErrorMapperTest {

    @Test
    fun noInternetErrorMapCorrectly() {
        val mapper = ErrorMapper()

        val result = mapper(NetworkError.NoInternet)

        Truth.assertThat(result).isEqualTo(R.string.internet_connection_is_unavailable)
    }

    @Test
    fun timeOutErrorMapCorrectly() {
        val mapper = ErrorMapper()

        val result = mapper(NetworkError.Timeout)

        Truth.assertThat(result).isEqualTo(R.string.we_have_some_connection_issues)
    }

    @Test
    fun serverErrorMapCorrectly() {
        val mapper = ErrorMapper()

        val result = mapper(NetworkError.Server)

        Truth.assertThat(result).isEqualTo(R.string.it_s_not_you_it_s_us)
    }

    @Test
    fun generalErrorMapCorrectly() {
        val mapper = ErrorMapper()

        val result = mapper(Exception())

        Truth.assertThat(result).isEqualTo(R.string.something_went_wrong)
    }
}
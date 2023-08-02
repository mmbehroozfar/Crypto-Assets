package com.yoti.android.cryptocurrencychallenge.util

import java.io.InputStreamReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {

    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/v2/assets" -> MockResponse().setResponseCode(200)
                    .setBody(getJsonContent("assets.json"))

                "/v2/markets?baseId=bitcoin" -> MockResponse().setResponseCode(200)
                    .setBody(getJsonContent("markets.json"))

                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}
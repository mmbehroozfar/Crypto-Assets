package com.yoti.android.cryptocurrencychallenge.ui.asset.detail

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.yoti.android.cryptocurrencychallenge.util.BaseFragmentTest
import com.yoti.android.cryptocurrencychallenge.util.launchFragmentInHiltContainer
import com.yoti.ui.asset.detail.AssetDetailFragment
import com.yoti.ui.home.R
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Test

@HiltAndroidTest
class AssetDetailFragmentTest : BaseFragmentTest() {

    @Test
    fun loadAndDisplayDataCorrectly() {
        launchFragmentInHiltContainer<AssetDetailFragment>(fragmentArgs = bundleOf("id" to "bitcoin"))

        onView(withId(R.id.exchange_id_tv)).check(matches(withText(Matchers.containsString("binance"))))
        onView(withId(R.id.symbol_tv)).check(matches(withText(Matchers.containsString("BTC"))))
        onView(withId(R.id.rank_tv)).check(matches(withText(Matchers.containsString("2"))))
        onView(withId(R.id.price_tv)).check(matches(withText(Matchers.containsString("26826.8163587828959259"))))
        onView(withId(R.id.updated_tv)).check(matches(withText(Matchers.containsString("22/05/2023"))))
    }
}
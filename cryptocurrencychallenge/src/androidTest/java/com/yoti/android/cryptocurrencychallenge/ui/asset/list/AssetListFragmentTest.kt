package com.yoti.android.cryptocurrencychallenge.ui.asset.list

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.yoti.android.cryptocurrencychallenge.util.BaseFragmentTest
import com.yoti.android.cryptocurrencychallenge.util.launchFragmentInHiltContainer
import com.yoti.ui.asset.list.AssetListFragment
import com.yoti.ui.asset.list.AssetListFragmentDirections
import com.yoti.ui.asset.list.AssetPagingAdapter
import com.yoti.ui.home.R
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

@HiltAndroidTest
class AssetListFragmentTest : BaseFragmentTest() {

    @Test
    fun loadAndDisplayDataCorrectly() {
        launchFragmentInHiltContainer<AssetListFragment>()

        onView(withText("Bitcoin")).check(matches(isDisplayed()))
        onView(withText("Ethereum")).check(matches(isDisplayed()))
    }

    @Test
    fun clickOnItemsShouldCallNavigation() {
        val navController = mockk<NavController>()
        launchFragmentInHiltContainer<AssetListFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        every {
            navController.navigate(
                AssetListFragmentDirections.actionAssetListFragmentToAssetDetailFragment(
                    "bitcoin"
                )
            )
        } returns Unit

        onView(withId(R.id.asset_rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AssetPagingAdapter.AssetViewHolder>(
                    0,
                    click()
                )
            )

        verify(exactly = 1) {
            navController.navigate(
                AssetListFragmentDirections.actionAssetListFragmentToAssetDetailFragment(
                    "bitcoin"
                )
            )
        }
    }
}
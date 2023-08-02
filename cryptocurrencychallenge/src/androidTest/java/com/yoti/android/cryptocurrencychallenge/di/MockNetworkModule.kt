package com.yoti.android.cryptocurrencychallenge.di

import com.yoti.data.remote.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class MockNetworkModule : NetworkModule() {

    override fun baseUrl() = "http://localhost:8080/"

}

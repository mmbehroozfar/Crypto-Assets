package com.yoti.ui.asset.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.yoti.domain.base.Result
import com.yoti.domain.usecase.market.GetHighestMarketInformation24HrUseCase
import com.yoti.domain.usecase.market.UpdateMarketInformationUseCase
import com.yoti.test_shared.CoroutinesTestExtension
import com.yoti.ui.asset.FakeData.marketDomain
import com.yoti.ui.asset.FakeData.marketUi
import com.yoti.ui.asset.mapper.ErrorMapper
import com.yoti.ui.asset.mapper.MarketMapper
import com.yoti.ui.home.R
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesTestExtension::class)
class AssetDetailViewModelTest {

    @RelaxedMockK
    lateinit var updateMarketInformationUseCase: UpdateMarketInformationUseCase

    @RelaxedMockK
    lateinit var getHighestMarketInformation24HrUseCase: GetHighestMarketInformation24HrUseCase

    @RelaxedMockK
    lateinit var marketMapper: MarketMapper

    @RelaxedMockK
    lateinit var errorMapper: ErrorMapper

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initiallyShouldCallUseCases() {
        createViewModel()

        coVerify(exactly = 1) { updateMarketInformationUseCase(id) }
        coVerify(exactly = 1) { getHighestMarketInformation24HrUseCase(id) }
    }

    @Test
    fun successResultShouldUpdateState() = runTest {
        coEvery { updateMarketInformationUseCase(id) } returns Result.Success(Unit)
        coEvery { getHighestMarketInformation24HrUseCase(id) } returns flowOf(marketDomain)
        coEvery { marketMapper(marketDomain) } returns marketUi

        val viewModel = createViewModel()

        Truth.assertThat(viewModel.market.value).isEqualTo(marketUi)
    }

    @Test
    fun errorResultShouldUpdateState() = runTest {
        val exception = Exception()
        val errorRes = R.string.it_s_not_you_it_s_us
        coEvery { updateMarketInformationUseCase(id) } returns Result.Error(exception)
        every { errorMapper(exception) } returns errorRes
        val viewModel = createViewModel()

        viewModel.showError.test {
            val item = awaitItem()

            Truth.assertThat(item).isEqualTo(errorRes)
        }
    }

    @Test
    fun afterLoadShouldChangeLoadingState() {
        val viewModel = createViewModel()

        Truth.assertThat(viewModel.isLoading.value).isFalse()
    }

    private fun createViewModel() = AssetDetailViewModel(
        savedStateHandle = SavedStateHandle(mapOf("id" to id)),
        updateMarketInformationUseCase = updateMarketInformationUseCase,
        getHighestMarketInformation24HrUseCase = getHighestMarketInformation24HrUseCase,
        marketMapper = marketMapper,
        errorMapper = errorMapper,
    )

    private companion object {
        const val id = "bitcoin"
    }
}
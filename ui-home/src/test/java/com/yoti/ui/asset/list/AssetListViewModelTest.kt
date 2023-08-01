package com.yoti.ui.asset.list

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.yoti.domain.base.Result
import com.yoti.domain.usecase.asset.ObservePagedAssetsUseCase
import com.yoti.domain.usecase.asset.UpdateAssetsUseCase
import com.yoti.test_shared.CoroutinesTestExtension
import com.yoti.ui.asset.mapper.AssetMapper
import com.yoti.ui.asset.mapper.ErrorMapper
import com.yoti.ui.home.R
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesTestExtension::class)
class AssetListViewModelTest {

    @RelaxedMockK
    lateinit var observePagedAssetsUseCase: ObservePagedAssetsUseCase

    @RelaxedMockK
    lateinit var updateAssetsUseCase: UpdateAssetsUseCase

    @RelaxedMockK
    lateinit var assetMapper: AssetMapper

    @RelaxedMockK
    lateinit var errorMapper: ErrorMapper

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initiallyShouldCallUseCases() {
        createViewModel()

        coVerify(exactly = 1) { updateAssetsUseCase(Unit) }
        coVerify(exactly = 1) { observePagedAssetsUseCase(Unit) }
    }

    @Test
    fun afterLoadShouldChangeLoadingState() {
        val viewModel = createViewModel()

        Truth.assertThat(viewModel.isLoading.value).isFalse()
    }

    @Test
    fun errorResultShouldUpdateState() = runTest {
        val exception = Exception()
        val errorRes = R.string.something_went_wrong
        coEvery { updateAssetsUseCase(Unit) } returns Result.Error(exception)
        every { errorMapper(exception) } returns errorRes
        val viewModel = createViewModel()

        viewModel.showError.test {
            val item = awaitItem()

            Truth.assertThat(item).isEqualTo(errorRes)
        }
    }

    private fun createViewModel() = AssetListViewModel(
        observePagedAssetsUseCase = observePagedAssetsUseCase,
        updateAssetsUseCase = updateAssetsUseCase,
        assetMapper = assetMapper,
        errorMapper = errorMapper,
    )
}
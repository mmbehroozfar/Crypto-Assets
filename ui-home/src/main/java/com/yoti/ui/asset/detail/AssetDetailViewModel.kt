package com.yoti.ui.asset.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoti.domain.base.onError
import com.yoti.domain.usecase.market.GetHighestMarketInformation24HrUseCase
import com.yoti.domain.usecase.market.UpdateMarketInformationUseCase
import com.yoti.ui.asset.mapper.ErrorMapper
import com.yoti.ui.asset.mapper.MarketMapper
import com.yoti.ui.asset.model.MarketUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class AssetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updateMarketInformationUseCase: UpdateMarketInformationUseCase,
    private val getHighestMarketInformation24HrUseCase: GetHighestMarketInformation24HrUseCase,
    private val marketMapper: MarketMapper,
    private val errorMapper: ErrorMapper,
) : ViewModel() {

    private val _market = MutableStateFlow(MarketUi())
    val market: StateFlow<MarketUi> get() = _market

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _showError = MutableSharedFlow<Int>(replay = 0)
    val showError: SharedFlow<Int> get() = _showError

    private var assetId = ""

    init {
        savedStateHandle.get<String>("id")?.let {
            assetId = it
            updateMarketInformation()
            getMarketInformation()
        }
    }

    fun updateMarketInformation() {
        viewModelScope.launch {
            _isLoading.emit(true)
            updateMarketInformationUseCase(assetId).let {
                it.onError { exception ->
                    _showError.emit(errorMapper(exception))
                }
                _isLoading.emit(false)
            }
        }
    }

    private fun getMarketInformation() {
        getHighestMarketInformation24HrUseCase(assetId)
            .filterNotNull()
            .map {
                marketMapper(it)
            }
            .onEach {
                _market.emit(it)
            }
            .launchIn(viewModelScope)
    }
}
package com.yoti.ui.asset.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.yoti.domain.base.onError
import com.yoti.domain.extension.mapPagingData
import com.yoti.domain.usecase.asset.ObservePagedAssetsUseCase
import com.yoti.domain.usecase.asset.UpdateAssetsUseCase
import com.yoti.ui.asset.mapper.AssetMapper
import com.yoti.ui.asset.mapper.ErrorMapper
import com.yoti.ui.asset.model.AssetUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AssetListViewModel @Inject constructor(
    observePagedAssetsUseCase: ObservePagedAssetsUseCase,
    private val updateAssetsUseCase: UpdateAssetsUseCase,
    private val assetMapper: AssetMapper,
    private val errorMapper: ErrorMapper,
) : ViewModel() {

    private val _assets = observePagedAssetsUseCase.flow.mapPagingData { assetMapper(it) }
    val assets: Flow<PagingData<AssetUi>> get() = _assets

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _showError = Channel<Int>(Channel.BUFFERED)
    val showError: Flow<Int> get() = _showError.receiveAsFlow()

    init {
        observePagedAssetsUseCase(Unit)
        updateAssets()
    }

    fun updateAssets() {
        viewModelScope.launch {
            _isLoading.emit(true)
            updateAssetsUseCase(Unit).let {
                it.onError { exception ->
                    _showError.send(errorMapper(exception))
                }
                _isLoading.emit(false)
            }
        }
    }
}
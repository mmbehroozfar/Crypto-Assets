package com.yoti.ui.asset.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yoti.ui.asset.util.viewBinding
import com.yoti.ui.home.R
import com.yoti.ui.home.databinding.FragmentAssetDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AssetDetailFragment : Fragment(R.layout.fragment_asset_detail) {

    private val viewModel by viewModels<AssetDetailViewModel>()
    private val binding by viewBinding(FragmentAssetDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() = with(binding) {
        containerSrl.setOnRefreshListener {
            viewModel.updateMarketInformation()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.market.collect {
                    with(binding) {
                        symbolTv.text = getString(R.string.symbol, it.symbol)
                        exchangeIdTv.text = getString(R.string.exchange_id, it.exchangeId)
                        rankTv.text = getString(R.string.rank, it.rank)
                        priceTv.text = getString(R.string.price, it.price)
                        updatedTv.text = getString(R.string.updated_at, it.updated)
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showError.collect {
                    Toast.makeText(
                        requireContext(),
                        getString(it),
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    binding.containerSrl.isRefreshing = it
                }
            }
        }
    }

}
package com.yoti.ui.asset.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.yoti.ui.asset.util.autoCleared
import com.yoti.ui.asset.util.viewBinding
import com.yoti.ui.home.R
import com.yoti.ui.home.databinding.FragmentAssetListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AssetListFragment : Fragment(R.layout.fragment_asset_list) {

    private val viewModel by viewModels<AssetListViewModel>()
    private val binding by viewBinding(FragmentAssetListBinding::bind)
    private var adapter by autoCleared<AssetPagingAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initRecyclerView()
        initObservers()
    }

    private fun initListeners() = with(binding) {
        containerSrl.setOnRefreshListener {
            viewModel.updateAssets()
        }
    }

    private fun initAdapter() {
        adapter = AssetPagingAdapter(
            onItemClicked = {
                findNavController().navigate(
                    AssetListFragmentDirections.actionAssetListFragmentToAssetDetailFragment(
                        it
                    )
                )
            },
        )
        lifecycleScope.launch {
            viewModel.assets.collectLatest(adapter::submitData)
        }
    }

    private fun initRecyclerView() = with(binding) {
        assetRv.adapter = adapter
    }

    private fun initObservers() {
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
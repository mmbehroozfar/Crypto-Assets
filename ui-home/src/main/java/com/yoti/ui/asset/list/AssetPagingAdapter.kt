package com.yoti.ui.asset.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yoti.ui.asset.model.AssetUi
import com.yoti.ui.home.databinding.ItemAssetBinding

class AssetPagingAdapter(private val onItemClicked: (String) -> Unit) :
    PagingDataAdapter<AssetUi, AssetPagingAdapter.AssetViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(
            binding = ItemAssetBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onItemClicked = onItemClicked,
        )
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class AssetViewHolder(
        private val binding: ItemAssetBinding,
        private val onItemClicked: (String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AssetUi) {
            binding.assetItemCv.setOnClickListener {
                onItemClicked(item.id)
            }

            binding.nameTv.text = item.name
            binding.symbolTv.text = item.symbol
            binding.priceTv.text = item.price
        }

    }

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<AssetUi>() {
            override fun areItemsTheSame(oldItem: AssetUi, newItem: AssetUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AssetUi, newItem: AssetUi): Boolean {
                return oldItem.name == newItem.name && oldItem.price == newItem.price && oldItem.symbol == newItem.symbol
            }
        }
    }

}
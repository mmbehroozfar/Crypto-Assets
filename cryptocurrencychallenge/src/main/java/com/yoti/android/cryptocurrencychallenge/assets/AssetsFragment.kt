package com.yoti.android.cryptocurrencychallenge.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yoti.android.cryptocurrencychallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_assets, container, false)
    }
}
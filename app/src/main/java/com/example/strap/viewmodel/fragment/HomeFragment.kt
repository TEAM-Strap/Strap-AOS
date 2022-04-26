package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.View
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override val FRAGMENT_TAG: String
        get() = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}
package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.View
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentMypageBinding

class MypageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val FRAGMENT_TAG: String
        get() = "MypageFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}
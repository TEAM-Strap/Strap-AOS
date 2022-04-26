package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.View
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentGroupBinding

class GroupFragment : BaseFragment<FragmentGroupBinding>(R.layout.fragment_group) {
    override val FRAGMENT_TAG: String
        get() = "GroupFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}
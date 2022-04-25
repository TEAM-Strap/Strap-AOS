package com.example.strap.viewmodel

import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentFindgroupBinding

class FindGroup : BaseFragment<FragmentFindgroupBinding>(R.layout.fragment_findgroup) {
    override val FRAGMENT_TAG: String
        get() = R.layout.fragment_findgroup.toString()
}
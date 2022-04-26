package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.View
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentCalendarBinding

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {
    override val FRAGMENT_TAG: String
        get() = "CalendarFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}
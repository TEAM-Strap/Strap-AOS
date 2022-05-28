package com.example.strap.viewmodel.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentCalendarBinding
import com.example.strap.viewmodel.activity.*
import com.example.strap.viewmodel.activity.AddRoutineActivity.adapter

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {
    lateinit var recyclerView :RecyclerView
    override val FRAGMENT_TAG: String
        get() = "CalendarFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, null)
        recyclerView = view.findViewById(R.id.recyclerView_for_calendar)

        initRecyclerView(recyclerView)

        val history:LinearLayout = view.findViewById(R.id.history_of_exercise)
        CalendarAdapter.history = history

        return view
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = CalendarAdapter();
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        (recyclerView.adapter as CalendarAdapter).setCalendar(ArrayList<DayCard>())
        for (i in 1..31) {
            when {
                i % 7 == 0 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Sat", i))
                i % 7 == 1 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Sun", i))
                i % 7 == 2 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Mon", i))
                i % 7 == 3 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Tue", i))
                i % 7 == 4 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Wed", i))
                i % 7 == 5 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Thu", i))
                i % 7 == 6 -> (recyclerView.adapter as CalendarAdapter).addDay(DayCard("Fri", i))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}
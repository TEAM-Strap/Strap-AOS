package com.example.strap.viewmodel.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.strap.R
import com.example.strap.databinding.FragmentAddGroupCommunityBinding
import com.example.strap.entity.Community
import com.example.strap.viewmodel.activity.SelectFitnessActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddCommunityFragment :
    Fragment() {

    private lateinit var binding: FragmentAddGroupCommunityBinding
    private val itemClicked = arrayListOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )

    private val activity by lazy {
        requireActivity() as SelectFitnessActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_group_community,
            container,
            false
        )

        initViews()
        bindViews()
        setStartTime()
        setEndTime()
        addGroupCommunity()

        return binding.root
    }

    private fun initViews() = with(binding) {
        val currentTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        startHour.text = currentTime
        endHour.text = currentTime
    }

    private fun bindViews() = with(binding) {
        btnMon.setOnClickListener {
            if (!checkClicked(0)) {
                changeClickedState(it, 0)
            } else if (checkClicked(0)) {
                changeUnClickedState(it, 0)
            }
        }
        btnTue.setOnClickListener {
            if (!checkClicked(1)) {
                changeClickedState(it, 1)
            } else if (checkClicked(1)) {
                changeUnClickedState(it, 1)
            }
        }
        btnWed.setOnClickListener {
            if (!checkClicked(2)) {
                changeClickedState(it, 2)
            } else if (checkClicked(2)) {
                changeUnClickedState(it, 2)
            }
        }
        btnThr.setOnClickListener {
            if (!checkClicked(3)) {
                changeClickedState(it, 3)
            } else if (checkClicked(3)) {
                changeUnClickedState(it, 3)
            }
        }
        btnFri.setOnClickListener {
            if (!checkClicked(4)) {
                changeClickedState(it, 4)
            } else if (checkClicked(4)) {
                changeUnClickedState(it, 4)
            }
        }
        btnSat.setOnClickListener {
            if (!checkClicked(5)) {
                changeClickedState(it, 5)
            } else if (checkClicked(5)) {
                changeUnClickedState(it, 5)
            }
        }
        btnSun.setOnClickListener {
            if (!checkClicked(6)) {
                changeClickedState(it, 6)
            } else if (checkClicked(6)) {
                changeUnClickedState(it, 6)
            }
        }
    }

    private fun checkClicked(index: Int): Boolean {
        return itemClicked[index]
    }

    private fun changeClickedState(view: View, index: Int) {
        view.setBackgroundResource(R.drawable.btn_day_yes_pick)
        itemClicked[index] = true
    }

    private fun changeUnClickedState(view: View, index: Int) {
        view.setBackgroundResource(R.drawable.btn_day_no_pick)
        itemClicked[index] = false
    }

    private fun setStartTime() = with(binding) {
        btnSelectStartTime.setOnClickListener {
            val timePicker =
                TimePickerDialog(activity, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        if (minute == 0) binding.startHour.text = "${hourOfDay}:00"
                        else {
                            binding.startHour.text = "${hourOfDay}:${minute}"
                        }
                    }
                }, 0, 0, true)

            timePicker.show()
        }
    }

    private fun setEndTime() = with(binding) {
        btnSelectEndTime.setOnClickListener {
            val timePicker =
                TimePickerDialog(activity, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        if (minute == 0) binding.endHour.text = "${hourOfDay}:00"
                        else {
                            binding.endHour.text = "${hourOfDay}:${minute}"
                        }
                    }
                }, 0, 0, true)

            timePicker.show()
        }
    }

    private fun addGroupCommunity() = with(binding) {
        btnAddGroup.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                activity.db
                    .collection("database")
                    .document("fitness")
                    .collection("community")
                    .add(
                        Community(
                            titleEditTextView.text.toString(),
                            "",
                            activity.uid,
                            listOf(),
                            listOf(),
                            descEditTextView.text.toString(),
                        )
                    )
            }
        }
    }
}
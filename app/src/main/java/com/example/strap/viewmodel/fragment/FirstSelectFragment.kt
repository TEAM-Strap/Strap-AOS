package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.strap.R
import com.example.strap.databinding.ItemSelectFitnessViewpagerFirstBinding
import com.example.strap.viewmodel.activity.SelectFitnessActivity

class FirstSelectFragment : Fragment() {
    private lateinit var binding: ItemSelectFitnessViewpagerFirstBinding

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
            R.layout.item_select_fitness_viewpager_first,
            container,
            false
        )

        binding.lifecycleOwner = this@FirstSelectFragment
        bindViews()

        return binding.root
    }

    private fun bindViews() = with(binding) {
        btnStart.setOnClickListener {
            activity.saveAndChangeFragment(activity.binding.frameLayout.id, SecondSelectFragment())
        }
    }
}
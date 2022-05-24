package com.example.strap.viewmodel.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.strap.R
import com.example.strap.databinding.ItemSelectFitnessViewpagerFifthBinding
import com.example.strap.viewmodel.activity.MainActivity
import com.example.strap.viewmodel.activity.SelectFitnessActivity

class FifthSelectFragment : Fragment() {
    private lateinit var binding: ItemSelectFitnessViewpagerFifthBinding
    private var title: String? = null

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
            R.layout.item_select_fitness_viewpager_fifth,
            container,
            false
        )

        initViews()
        bindViews()

        return binding.root
    }

    private fun initViews() {
        val bundle = arguments

        if (bundle != null) {
            title = bundle.getString("title")
        }
    }

    private fun bindViews() = with(binding) {
        thirdTextView.text = title

        btnStart.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

            activity.finish()
        }
    }
}
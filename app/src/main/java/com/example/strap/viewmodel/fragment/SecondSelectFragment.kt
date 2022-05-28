package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.strap.R
import com.example.strap.adapter.FitnessItemAdapter
import com.example.strap.databinding.ItemSelectFitnessViewpagerSecondBinding
import com.example.strap.entity.Fitness
import com.example.strap.viewmodel.activity.SelectFitnessActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class SecondSelectFragment : Fragment() {
    private lateinit var binding: ItemSelectFitnessViewpagerSecondBinding

    private val fitnessItems: MutableList<Fitness> = mutableListOf()

    private val adapter by lazy {
        FitnessItemAdapter(itemClickListener = { fitness ->
            val bundle = Bundle()
            bundle.putStringArrayList("community", ArrayList(fitness.community!!))

            val fragment = ThirdSelectFragment()
            fragment.arguments = bundle
            activity.saveAndChangeFragment(activity.binding.frameLayout.id, fragment)
        })
    }

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
            R.layout.item_select_fitness_viewpager_second,
            container,
            false
        )

        binding.lifecycleOwner = this@SecondSelectFragment
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        CoroutineScope(Dispatchers.IO).launch {
            activity.db
                .collection("database")
                .document("fitness")
                .collection("info")
                .get()
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        fitnessItems.clear()

                        for (fitness in task.result.documents) {
                            fitnessItems.add(fitness.toObject(Fitness::class.java)!!)
                        }
                    }

                    adapter.submitList(fitnessItems)
                }
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        hideProgress()
    }

    private fun hideProgress() = with(binding) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
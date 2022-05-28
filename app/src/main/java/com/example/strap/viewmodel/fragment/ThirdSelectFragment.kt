package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.strap.R
import com.example.strap.adapter.CommunityItemAdapter
import com.example.strap.databinding.ItemSelectFitnessViewpagerThirdBinding
import com.example.strap.entity.Community
import com.example.strap.viewmodel.activity.SelectFitnessActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThirdSelectFragment : Fragment() {
    private lateinit var binding: ItemSelectFitnessViewpagerThirdBinding

    private var communityList: List<String> = listOf()
    private val communityItems: MutableList<Community> = mutableListOf()


    private val adapter by lazy {
        CommunityItemAdapter(itemClickListener = { position ->
            val bundle = Bundle()
            bundle.putString("id", communityList[position])

            val fragment = FourthSelectFragment()
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
            R.layout.item_select_fitness_viewpager_third,
            container,
            false
        )

        binding.lifecycleOwner = this@ThirdSelectFragment

        initViews()
        initRecyclerView()

        return binding.root
    }

    private fun initViews() {
        val bundle = arguments

        if (bundle != null) {
            communityList = bundle.getStringArrayList("community")!!.toList()
        }
    }

    private fun initRecyclerView() = with(binding) {
        CoroutineScope(Dispatchers.IO).launch {
            activity.db
                .collection("database")
                .document("fitness")
                .collection("community")
                .get()
                .addOnCompleteListener { task ->
                    communityItems.clear()

                    if(task.isSuccessful) {
                        for (document in task.result.documents) {
                            if (communityList.contains(document.id)) {
                                val data = document.toObject(Community::class.java)!!
                                communityItems.add(data)
                            }
                        }

                        adapter.submitList(communityItems)
                    }
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
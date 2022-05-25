package com.example.strap.viewmodel.fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentHomeBinding
import com.example.strap.viewmodel.activity.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    val requestCodeForRoutine = 100
    lateinit var recyclerView :RecyclerView

    override val FRAGMENT_TAG: String
        get() = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)
        recyclerView = view.findViewById(R.id.recyclerView_for_main)

        initRecyclaView(recyclerView)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestCodeForRoutine) {
            if (resultCode == RESULT_OK) run {
                val name = data?.getStringExtra("name") +
                        ((recyclerView.adapter as HomeAdapter).itemCount)
                (recyclerView.adapter as HomeAdapter).addItem(Routine(name, 0, 0, 0))
                (recyclerView.adapter as HomeAdapter).notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclaView(recyclerView: RecyclerView) {
        recyclerView.adapter = HomeAdapter();
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        (recyclerView.adapter as HomeAdapter).setOnItemClickListener(object: HomeAdapter.OnItemClickListener
        {
            override fun onItemClick(v: View?, position: Int) {
                val intent = Intent(context, AddRoutineActivity::class.java)
                startActivityForResult(intent, requestCodeForRoutine)
            }
        })

        (recyclerView.adapter as HomeAdapter).setItems(ArrayList<AdapterType>())
        (recyclerView.adapter as HomeAdapter).addItem(AddButton())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}
package com.example.strap.viewmodel.fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
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
    val requestCodeForPopRoutine = 200
    lateinit var recyclerView :RecyclerView
    lateinit var moveButton :Button;

    override val FRAGMENT_TAG: String
        get() = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)
        recyclerView = view.findViewById(R.id.recyclerView_for_main)

        initRecyclerView(recyclerView)

        moveButton = view.findViewById(R.id.routine_of_today)
        moveButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, TodayRoutineActivity::class.java)
            startActivityForResult(intent, requestCodeForPopRoutine)
        })

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
        else if (requestCode == requestCodeForPopRoutine) {
            if (resultCode == RESULT_OK) run {
                val title = data?.getStringExtra("routineTitle")
                if (title.equals("숀리의 다이어트 운동법")) {
                    (recyclerView.adapter as HomeAdapter).addItem(Routine(title, 0, 0, 0))
                    val recommendeditems = ArrayList<AdapterType>(10);
                    recommendeditems.add(Routine("전신 - 물개 운동", 0, 15, 3));
                    recommendeditems.add(Routine("상체 - 돌핀 푸시업", 0, 15, 3));
                    recommendeditems.add((Routine("하체 - 도마뱀 운동", 0, 15, 3)));
                    recommendeditems.add(AddButton())
                    AddRoutineActivity.adapter.setItems(recommendeditems)
                    AddRoutineActivity.adapter.notifyDataSetChanged()
                    (recyclerView.adapter as HomeAdapter).notifyDataSetChanged()

                }
            }
        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
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
package com.example.strap.viewmodel.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.strap.R
import com.example.strap.adapter.CommunityRankItemAdapter
import com.example.strap.adapter.CommunityRoutineItemAdapter
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentGroupBinding
import com.example.strap.entity.Routine
import com.example.strap.entity.User

class GroupFragment : BaseFragment<FragmentGroupBinding>(R.layout.fragment_group) {
    override val FRAGMENT_TAG: String
        get() = "GroupFragment"

    private val rankItemList: List<User> = listOf(
        User(
            "http://www.engjournal.co.kr/news/photo/202103/1383_2106_476.jpg",
            null,
            "3대 1000 김재현",
            null,
            null
        ),
        User("https://avatars.githubusercontent.com/u/33795856?v=4", null, "컴공 17학번", null, null),
        User("https://avatars.githubusercontent.com/u/103251717?v=4", null, "어깨왕", null, null),
        User(
            "https://www.naewoeilbo.com/news/photo/202111/423330_198806_4551.png",
            null,
            "졸업하고싶다",
            null,
            null
        ),
    )

    private val routineItemList: List<Routine> = listOf(
        Routine(""),
        Routine(""),
        Routine(""),
        Routine(""),
    )

    private val rankAdapter by lazy {
        CommunityRankItemAdapter()
    }

    private val routineAdapter by lazy {
        CommunityRoutineItemAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        memberRecyclerView.adapter = rankAdapter
        memberRecyclerView.layoutManager = LinearLayoutManager(activity)
        rankAdapter.submitList(rankItemList)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        routineRecyclerView.adapter = routineAdapter
        routineRecyclerView.layoutManager = layoutManager

        routineAdapter.submitList(routineItemList)
    }
}
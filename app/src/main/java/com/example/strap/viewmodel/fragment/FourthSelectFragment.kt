package com.example.strap.viewmodel.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.strap.R
import com.example.strap.adapter.BadgeAdapter
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentGroupInfoBinding
import com.example.strap.databinding.ItemSelectFitnessViewpagerFourthBinding
import com.example.strap.databinding.ItemSelectFitnessViewpagerThirdBinding
import com.example.strap.entity.Badge
import com.example.strap.entity.Community
import com.example.strap.entity.User
import com.example.strap.viewmodel.activity.MainActivity
import com.example.strap.viewmodel.activity.SelectFitnessActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.*

class FourthSelectFragment : Fragment() {
    private lateinit var communityId: String
    private lateinit var community: Community
    private lateinit var binding: ItemSelectFitnessViewpagerFourthBinding

    private val badges = arrayListOf(
        Badge(
            1,
            "근성왕",
            "2022/05/01"
        ),
        Badge(
            2,
            "가슴왕",
            "2022/02/01"
        ),
        Badge(
            1,
            "어깨왕",
            "2022/02/01"
        )
    )

    private val adapter by lazy {
        BadgeAdapter(badges)
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
            R.layout.item_select_fitness_viewpager_fourth,
            container,
            false
        )

        val bundle = arguments
        if (bundle != null) {
            communityId = bundle.getString("id")!!
        }

        bindViews()

        return binding.root
    }

    private fun bindViews() {
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            activity.db
                .collection("database")
                .document("fitness")
                .collection("community")
                .addSnapshotListener { value, error ->
                    value?.documents!!.forEach { snapShot ->
                        if (snapShot.id == communityId) {
                            community = snapShot.toObject(Community::class.java)!!
                        }
                    }
                }

            delay(10)

            withContext(Dispatchers.Main) {
                Glide.with(binding.groupImageView)
                    .load(community?.image)
                    .centerCrop()
                    .into(binding.groupImageView)
                binding.memberTextView.text = "방장 외 ${community?.member?.size!!}명"
                binding.contentTextView.text = community?.description
            }
        }

        binding.btnParticipateGroup.setOnClickListener {
            updateCommunityMember()
            findSnapShotId()

            val bundle = Bundle()
            bundle.putString("title", community.title)

            val fragment = FifthSelectFragment()
            fragment.arguments = bundle

            activity.saveAndChangeFragment(activity.binding.frameLayout.id, fragment)
        }
    }

    private fun updateCommunityMember() {
        var member: List<String>?

        val element = activity.db
            .collection("database")
            .document("fitness")
            .collection("community")
            .document(communityId)


        // 기존의 그룹 커뮤니티 멤버를 불러오고
        element.get().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                member = task.result.toObject(Community::class.java)!!.member

                // 멤버 추가하고
                member = member?.plus(activity.uid)

                // Firestore 업데이트
                element.update(mapOf("member" to member))
                    .addOnCompleteListener {}
                    .addOnFailureListener {}
            }
        }
    }

    private fun findSnapShotId() {
        activity.db
            .collection("database")
            .document("user")
            .collection("info")

            .addSnapshotListener { value, error ->
                value?.documents?.forEach { snapShot ->
                    val data = snapShot?.toObject(User::class.java)

                    if (activity.uid == data?.token) {
                        updateUserState(snapShot?.id)
                    }
                }

            }

    }

    private fun updateUserState(snapShotId: String) {
        var user: User

        val element = activity.db
            .collection("database")
            .document("user")
            .collection("info")
            .document(snapShotId)

        // 유저 프로필을 불러오고
        element.addSnapshotListener { snapShot, error ->
            user = snapShot?.toObject(User::class.java)!!

            // 유저 업데이트
            user = User(user.profileImage, user.email, user.name, user.token, communityId)

            // Firestore 업데이트
            element.update(mapOf("community" to communityId))
                .addOnCompleteListener { }
                .addOnFailureListener { }
        }
    }

}
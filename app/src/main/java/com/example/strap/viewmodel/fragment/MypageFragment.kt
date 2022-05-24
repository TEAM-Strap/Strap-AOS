package com.example.strap.viewmodel.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.strap.R
import com.example.strap.base.BaseFragment
import com.example.strap.databinding.FragmentMypageBinding
import com.example.strap.viewmodel.activity.LoginActivity

class MypageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    override val FRAGMENT_TAG: String
        get() = "MypageFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViews()
    }

    private fun bindViews() {
        binding.btnLogout.setOnClickListener {
            activity.auth.signOut()
            activity.showToast(requireContext(), "로그아웃에 성공하였습니다.")

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity.finish()
        }
    }
}
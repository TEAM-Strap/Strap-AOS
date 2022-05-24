package com.example.strap.viewmodel.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.strap.R
import com.example.strap.base.BaseActivity
import com.example.strap.databinding.ActivityMainBinding
import com.example.strap.viewmodel.fragment.CalendarFragment
import com.example.strap.viewmodel.fragment.HomeFragment
import com.example.strap.viewmodel.fragment.MypageFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val ACTIVITY_TAG: String
        get() = R.layout.activity_main.toString()

    private val _auth by lazy {
        Firebase.auth
    }

    val auth: FirebaseAuth
        get() = _auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        binding.bottomMenu.selectedItemId = R.id.home_fragment
        changeFragment(binding.fragmentContainer.id, HomeFragment())
    }

    private fun bindViews() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_fragment -> {
                    changeFragment(binding.fragmentContainer.id, HomeFragment())
                }
                R.id.calendar_fragment -> {
                    changeFragment(binding.fragmentContainer.id, CalendarFragment())
                }
                R.id.group_fragment -> {
                    changeFragment(binding.fragmentContainer.id, CalendarFragment())
                }
                R.id.mypage_fragment -> {
                    changeFragment(binding.fragmentContainer.id, MypageFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
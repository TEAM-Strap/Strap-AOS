package com.example.strap.viewmodel.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.strap.R
import com.example.strap.base.BaseActivity
import com.example.strap.databinding.ActivityMainBinding
import com.example.strap.viewmodel.fragment.CalendarFragment
import com.example.strap.viewmodel.fragment.GroupFragment
import com.example.strap.viewmodel.fragment.HomeFragment
import com.example.strap.viewmodel.fragment.MypageFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val ACTIVITY_TAG: String
        get() = R.layout.activity_main.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        binding.bottomMenu.selectedItemId = R.id.home_fragment
        changeFragment(HomeFragment())
    }

    private fun bindViews() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_fragment -> {
                    changeFragment(HomeFragment())
                }
                R.id.calendar_fragment -> {
                    changeFragment(CalendarFragment())
                }
                R.id.group_fragment -> {
                    changeFragment(GroupFragment())
                }
                R.id.mypage_fragment -> {
                    changeFragment(MypageFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
package com.example.strap.viewmodel.activity

import android.os.Bundle
import android.util.Log
import com.example.strap.R
import com.example.strap.base.BaseActivity
import com.example.strap.databinding.ActivitySelectBinding
import com.example.strap.viewmodel.fragment.FirstSelectFragment
import com.google.firebase.firestore.FirebaseFirestore

class SelectFitnessActivity(override val ACTIVITY_TAG: String = "SelectFitnessActivity") :
    BaseActivity<ActivitySelectBinding>(R.layout.activity_select) {
    private var _uid: String? = null
    val uid: String
        get() = _uid ?: "No Exist"

    private val _db by lazy {
        FirebaseFirestore.getInstance()
    }
    val db: FirebaseFirestore
        get() = _db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _uid = intent.getStringExtra("uid") ?: "No Exist"
        initViews()
    }

    private fun initViews() = with(binding) {
        changeFragment(frameLayout.id, FirstSelectFragment())
    }
}
package com.example.strap

import android.os.Bundle
import com.example.strap.base.BaseActivity
import com.example.strap.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override val ACTIVITY_TAG: String
        get() = R.layout.activity_main.toString()
}
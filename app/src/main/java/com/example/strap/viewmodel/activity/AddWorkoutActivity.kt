package com.example.strap.viewmodel.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.strap.R

class AddWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workout)

        val workoutName = findViewById<EditText>(R.id.workout_name) as EditText
        val count = findViewById<EditText>(R.id.count) as EditText
        val weight = findViewById<EditText>(R.id.weight) as EditText
        val set = findViewById<EditText>(R.id.set) as EditText
        val completeButton = findViewById<Button>(R.id.complete)
        val cancelButton = findViewById<Button>(R.id.cancel_add_workout)
        completeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (workoutName.text.toString() == "" || count.text.toString() == ""
                    || weight.text.toString() == "" || set.text.toString() == "") {
                    Toast.makeText(applicationContext, "모든 항목을 입력해주세요.", Toast.LENGTH_LONG)
                        .show()
                }
                else {
                    val intent = Intent();
                    intent.putExtra("name", workoutName.text.toString())
                    intent.putExtra("count", count.text.toString())
                    intent.putExtra("weight", weight.text.toString())
                    intent.putExtra("set", set.text.toString())
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        })

        cancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                finish()
            }
        })
    }
}
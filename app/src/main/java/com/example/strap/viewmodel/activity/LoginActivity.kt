package com.example.strap.viewmodel.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.strap.R
import com.example.strap.base.BaseActivity
import com.example.strap.databinding.ActivityLoginBinding
import com.example.strap.entity.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class LoginActivity(override val ACTIVITY_TAG: String = "LoginActivity") :
    BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val auth by lazy {
        Firebase.auth
    }

    private val db by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeViews()
        login()
        sign()
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null) {
            checkParticipate()
        }
    }

    private fun changeViews() = with(binding) {
        btnLoginGuide.setOnClickListener {
            signViewGroup.visibility = View.GONE
            loginViewGroup.visibility = View.VISIBLE
        }
        btnSignGuide.setOnClickListener {
            loginViewGroup.visibility = View.GONE
            signViewGroup.visibility = View.VISIBLE
        }
    }

    private fun login() = with(binding) {
        btnLogin.setOnClickListener {
            if (checkLogin()) {
                loginAccount(
                    idEditTextView.text.toString(),
                    passwordEditTextView.text.toString()
                )
            } else {
                showToast(this@LoginActivity, "입력을 확인해주세요.")
                return@setOnClickListener
            }
        }
    }

    private fun sign() = with(binding) {
        btnSign.setOnClickListener {
            if (checkSign()) {
                createAccount(
                    signIdEditTextView.text.toString(),
                    signPasswordEditTextView.text.toString()
                )
            } else {
                showToast(this@LoginActivity, "입력을 확인해주세요.")
                return@setOnClickListener
            }
        }
    }

    private fun loginAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(this@LoginActivity, "로그인에 성공하였습니다.")
                    checkParticipate()
                } else {
                    showToast(this@LoginActivity, "로그인에 실패하였습니다.")
                }
            }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // db에 사용자 정보 별도로 저장
                    val data = User(
                        null,
                        email,
                        binding.nicknameEditTextView.text.toString(),
                        user!!.uid,
                        null,
                    )
                    db.collection("database").document("user").collection("info").add(data)

                    binding.signViewGroup.visibility = View.GONE
                    binding.loginViewGroup.visibility = View.VISIBLE
                    showToast(this@LoginActivity, "회원가입에 성공하였습니다.")
                } else {
                    showToast(this@LoginActivity, "회원가입에서 오류가 발생했습니다.\n다시 시도해주세요.")
                }
            }
    }

    // 로그인 입력 검증
    private fun checkLogin(): Boolean = with(binding) {
        return !(idEditTextView.text.isNullOrEmpty() || passwordEditTextView.text.isNullOrEmpty())
    }

    // 회원가입 입력 검증
    private fun checkSign(): Boolean = with(binding) {
        return !(signIdEditTextView.text.isNullOrEmpty() || signPasswordEditTextView.text.isNullOrEmpty() || checkPasswordEditTextView.text.isNullOrEmpty() || (signPasswordEditTextView.text.toString() != checkPasswordEditTextView.text.toString()))
    }

    private fun beginSelectFitnessActivity() {
        val intent = Intent(this@LoginActivity, SelectFitnessActivity::class.java)
        intent.putExtra("uid", auth.uid)
        startActivity(intent)
        finish()
    }

    private fun beginMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // 그룹 가입 여부 검증
    private fun checkParticipate() {
        CoroutineScope(Dispatchers.IO).launch {
            db.collection("database")
                .document("user")
                .collection("info")
                .get()
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        for (document in task.result.documents) {
                            val data = document.toObject(User::class.java)!!
                            if (auth.uid == data.token) {
                                if (data.community.isNullOrEmpty()) {
                                    beginSelectFitnessActivity()
                                } else {
                                    beginMainActivity()
                                }
                            }
                        }
                    }
                }
        }
    }

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
package com.example.strap.entity

data class User(
    val profileImage: String? = null,
    val email: String? = null,
    val name: String? = null,
    val token: String? = null,
    var community: String? = null
)

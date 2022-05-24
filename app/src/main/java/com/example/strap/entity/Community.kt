package com.example.strap.entity

import java.io.Serializable

data class Community(
    val title: String? = null,
    val image: String? = null,
    val leader: String? = null,
    val member: List<String>? = null,
    val routine: List<String>? = null,
    val description : String? = null
) : Serializable
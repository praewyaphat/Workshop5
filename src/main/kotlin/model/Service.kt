package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: Int = 0,
    val name: String,
    val description: String,
    val defaultDurationInMinutes: Int
)

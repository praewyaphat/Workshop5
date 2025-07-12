package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: Int = 0,
    val clientName: String,
    val clientEmail: String,
    val appointmentTime: String, // ISO 8601 เช่น 2025-07-12T14:30:00Z
    val serviceId: Int
)

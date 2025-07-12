package com.example.repository

import com.example.model.Appointment
import java.time.Instant
import java.time.Duration

class AppointmentRepository {
    private val appointments = mutableListOf<Appointment>()
    private var nextId = 1

    fun getAll(): List<Appointment> = appointments

    fun getById(id: Int): Appointment? = appointments.find { it.id == id }

    fun add(appointment: Appointment): Appointment {
        val newAppointment = appointment.copy(id = nextId++)
        appointments.add(newAppointment)
        return newAppointment
    }

    fun update(id: Int, updated: Appointment): Boolean {
        val index = appointments.indexOfFirst { it.id == id }
        if (index == -1) return false
        appointments[index] = updated.copy(id = id)
        return true
    }

    fun delete(id: Int): Boolean {
        return appointments.removeIf { it.id == id }
    }

    fun findByServiceAndTimeRange(serviceId: Int, start: Instant, end: Instant): List<Appointment> {
        return appointments.filter {
            it.serviceId == serviceId &&
                    Instant.parse(it.appointmentTime).let { time ->
                        time < end && time.plus(Duration.ofMinutes(30)) > start
                    }
        }
    }
}

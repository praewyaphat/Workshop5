package com.example.routes

import com.example.model.Appointment
import com.example.repository.AppointmentRepository
import com.example.repository.ServiceRepository
import com.example.service.AppointmentService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.appointmentRoutes(
    appointmentRepo: AppointmentRepository,
    serviceRepo: ServiceRepository
) {
    val service = AppointmentService(appointmentRepo, serviceRepo)

    route("/appointments") {
        get {
            call.respond(appointmentRepo.getAll())
        }

        post {
            val newAppointment = call.receive<Appointment>()
            val result = service.createAppointment(newAppointment)
            result.fold(
                onSuccess = { call.respond(HttpStatusCode.Created, it) },
                onFailure = { call.respond(HttpStatusCode.Conflict, it.message ?: "Double booking not allowed") }
            )
        }

        // PUT & DELETE optional (เหมือนกับ service)
    }
}

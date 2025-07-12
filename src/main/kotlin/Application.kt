package com.example

import com.example.repository.AppointmentRepository
import com.example.repository.ServiceRepository
import com.example.routes.appointmentRoutes
import com.example.routes.serviceRoutes
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import com.example.routes.apiInfoRoutes

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val serviceRepo = ServiceRepository()
    val appointmentRepo = AppointmentRepository()

    routing {
        apiInfoRoutes()
        serviceRoutes(serviceRepo)
        appointmentRoutes(appointmentRepo, serviceRepo)
    }
}

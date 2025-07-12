package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.apiInfoRoutes() {
    get("/") {
        call.respondText("""
            Available API endpoints:

            Services:
            GET /services
            POST /services
            PUT /services/{id}
            DELETE /services/{id}

            Appointments:
            GET /appointments
            POST /appointments
        """.trimIndent())
    }
}

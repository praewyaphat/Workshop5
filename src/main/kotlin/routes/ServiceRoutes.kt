package com.example.routes

import com.example.model.Service
import com.example.repository.ServiceRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.serviceRoutes(serviceRepo: ServiceRepository) {
    route("/services") {
        get {
            call.respond(serviceRepo.getAll())
        }

        post {
            val newService = call.receive<Service>()
            val saved = serviceRepo.add(newService)
            call.respond(HttpStatusCode.Created, saved)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest)
            val updated = call.receive<Service>()
            if (serviceRepo.update(id, updated)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (serviceRepo.delete(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

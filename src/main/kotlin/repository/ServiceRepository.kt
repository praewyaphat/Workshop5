package com.example.repository

import com.example.model.Service

class ServiceRepository {
    private val services = mutableListOf<Service>()
    private var nextId = 1

    fun getAll(): List<Service> = services

    fun getById(id: Int): Service? = services.find { it.id == id }

    fun add(service: Service): Service {
        val newService = service.copy(id = nextId++)
        services.add(newService)
        return newService
    }

    fun update(id: Int, updated: Service): Boolean {
        val index = services.indexOfFirst { it.id == id }
        if (index == -1) return false
        services[index] = updated.copy(id = id)
        return true
    }

    fun delete(id: Int): Boolean {
        return services.removeIf { it.id == id }
    }
}

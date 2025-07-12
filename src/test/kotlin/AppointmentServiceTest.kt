package com.example.service

import com.example.model.Appointment
import com.example.model.Service
import com.example.repository.AppointmentRepository
import com.example.repository.ServiceRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class AppointmentServiceTest {

    private val serviceRepo = ServiceRepository()
    private val appointmentRepo = AppointmentRepository()
    private val appointmentService = AppointmentService(appointmentRepo, serviceRepo)

    @Test
    fun testCreateAppointment_success() {
        // เตรียมข้อมูล service
        val service = Service(name = "Test Service", description = "desc", defaultDurationInMinutes = 30)
        val savedService = serviceRepo.add(service)

        // เตรียมข้อมูลนัดหมาย
        val appointment = Appointment(
            clientName = "Bookbig",
            clientEmail = "bookbig@example.com",
            appointmentTime = "2025-07-13T14:00:00Z",
            serviceId = savedService.id
        )

        val result = appointmentService.createAppointment(appointment)

        assertNotNull(result.getOrNull())
        assertEquals(savedService.id, result.getOrNull()?.serviceId)
    }

    @Test
    fun testCreateAppointment_doubleBooking_fail() {
        // เตรียมข้อมูล service
        val service = Service(name = "Test Service", description = "desc", defaultDurationInMinutes = 60)
        val savedService = serviceRepo.add(service)

        // นัดหมายแรก
        val appointment1 = Appointment(
            clientName = "Praew",
            clientEmail = "praew@example.com",
            appointmentTime = "2025-07-13T14:00:00Z",
            serviceId = savedService.id
        )

        val res1 = appointmentService.createAppointment(appointment1)
        assertNotNull(res1.getOrNull())

        // นัดหมายที่ทับซ้อน (ช่วงเวลาเดียวกัน)
        val appointment2 = Appointment(
            clientName = "King",
            clientEmail = "king@example.com",
            appointmentTime = "2025-07-13T14:00:00Z", //เวลาชน!!
            serviceId = savedService.id
        )

        val res2 = appointmentService.createAppointment(appointment2)
        assert(res2.isFailure) { "Expected failure due to double booking, but was success" }
    }
}

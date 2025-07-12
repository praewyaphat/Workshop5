package com.example.service

import com.example.model.Appointment
import com.example.repository.AppointmentRepository
import com.example.repository.ServiceRepository
import java.time.Instant
import java.time.Duration

class AppointmentService(
    private val appointmentRepo: AppointmentRepository,
    private val serviceRepo: ServiceRepository
) {

    fun createAppointment(appointment: Appointment): Result<Appointment> {
        val service = serviceRepo.getById(appointment.serviceId)
            ?: return Result.failure(Exception("Service not found"))

        // คำนวณช่วงเวลาเริ่มต้นและสิ้นสุด
        val startTime = Instant.parse(appointment.appointmentTime)
        val endTime = startTime.plus(Duration.ofMinutes(service.defaultDurationInMinutes.toLong()))

        // ตรวจสอบว่ามีการจองที่เวลาทับซ้อนหรือไม่
        println("Checking overlaps for service ${service.id} between $startTime and $endTime")  // ใส่ตรงนี้

        val overlapping = appointmentRepo.findByServiceAndTimeRange(service.id, startTime, endTime)

        println("Overlapping results: $overlapping")

        if (overlapping.isNotEmpty()) {
            return Result.failure(Exception("Time slot is already booked"))
        }

        // เพิ่มการจองใหม่
        val saved = appointmentRepo.add(appointment)
        return Result.success(saved)
    }
}

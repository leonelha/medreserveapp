package com.reserveclinic.reserve_service.domain.repositories;

import com.reserveclinic.reserve_service.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}

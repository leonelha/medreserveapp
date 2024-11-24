package com.reserveclinic.doctor_service.domain.repositories;

import com.reserveclinic.doctor_service.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}

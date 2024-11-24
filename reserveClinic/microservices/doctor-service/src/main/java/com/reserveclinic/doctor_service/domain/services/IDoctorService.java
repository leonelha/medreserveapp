package com.reserveclinic.doctor_service.domain.services;

import com.reserveclinic.doctor_service.domain.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {
    List<Doctor> getAllDoctors();

    Optional<Doctor> getDoctorById(Long id);

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(Long id, Doctor doctor);

    void deleteDoctor(Long id);
}
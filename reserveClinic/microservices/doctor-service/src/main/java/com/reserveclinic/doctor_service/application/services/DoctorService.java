package com.reserveclinic.doctor_service.application.services;

import com.reserveclinic.doctor_service.domain.model.Doctor;
import com.reserveclinic.doctor_service.domain.repositories.IDoctorRepository;
import com.reserveclinic.doctor_service.domain.services.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    @Autowired
    public DoctorService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}

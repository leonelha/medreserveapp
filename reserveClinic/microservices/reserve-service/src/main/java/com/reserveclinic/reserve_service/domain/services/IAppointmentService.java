package com.reserveclinic.reserve_service.domain.services;

import com.reserveclinic.reserve_service.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {

    List<Appointment> getAllAppointment();

    Optional<Appointment> getAppointmentById(Long id);

    Appointment createAppointment(Appointment appointment);

    Appointment updateAppointment(Long id, Appointment appointment);

    void deleteAppointment(Long id);
}

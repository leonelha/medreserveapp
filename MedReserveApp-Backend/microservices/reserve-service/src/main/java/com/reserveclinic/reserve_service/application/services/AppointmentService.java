package com.reserveclinic.reserve_service.application.services;

import com.reserveclinic.reserve_service.Config.ClientD;
import com.reserveclinic.reserve_service.domain.model.Appointment;
import com.reserveclinic.reserve_service.domain.repositories.IAppointmentRepository;
import com.reserveclinic.reserve_service.domain.services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository appointmentRepository;
    private final ClientD clientD;

    @Autowired
    public AppointmentService(IAppointmentRepository AppointmentRepository, ClientD clientD) {
        this.appointmentRepository = AppointmentRepository;
        this.clientD = clientD;
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment createAppointment(Appointment Appointment) {
        return appointmentRepository.save(Appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment Appointment) {
        Appointment.setId(id);
        return appointmentRepository.save(Appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }


    ///
    @Override
    public ClientDTO findClientByAppointmentId(Long idAppointment) {
        Appointment appointment = appointmentRepository.findById(idAppointment).orElseThrow();
        ClientDTO clientDTO = clientD.findClientByAppointmentId(appointment.getId());
        return ClientDTO.builder().
                firstName(clientDTO.getFirstName())
                .build();
    }
}

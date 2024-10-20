package AppointmentArchitecture.aplication.services;

import AppointmentArchitecture.domain.models.Appointment;
import AppointmentArchitecture.domain.repositories.IAppointmentRepository;
import AppointmentArchitecture.domain.services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository AppointmentRepository;

    @Autowired
    public AppointmentService(IAppointmentRepository AppointmentRepository) {
        this.AppointmentRepository = AppointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return AppointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return AppointmentRepository.findById(id);
    }

    @Override
    public Appointment createAppointment(Appointment Appointment) {
        return AppointmentRepository.save(Appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment Appointment) {
        Appointment.setId(id);
        return AppointmentRepository.save(Appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        AppointmentRepository.deleteById(id);
    }
}

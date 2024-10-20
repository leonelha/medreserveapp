package appointmentsArchitecture.domain.services;

import appointmentsArchitecture.domain.models.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentservice {
    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(Long id);

    Appointment createAppointment(Appointment appointment);

    Appointment updateDoctor(Long id, Appointment appointment);

    void deleteDoctor(Long id);
}

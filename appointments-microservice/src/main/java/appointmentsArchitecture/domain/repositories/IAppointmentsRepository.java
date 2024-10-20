package appointmentssArchitecture.domain.repositories;

import appointmentssArchitecture.domain.models.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentsRepository extends JpaRepository<Appointments, Long> {
}

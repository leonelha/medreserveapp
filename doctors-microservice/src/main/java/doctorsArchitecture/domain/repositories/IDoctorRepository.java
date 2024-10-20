package doctorsArchitecture.domain.repositories;

import doctorsArchitecture.domain.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}

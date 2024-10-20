package appointmentsArchitecture.infrastructure.persistence;

import appointmentsArchitecture.domain.models.Appointment;
import appointmentsArchitecture.domain.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AppointmentRepository implements IAppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllById(Iterable<Long> ids) {
        return appointmentRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return appointmentRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void delete(Appointment entity) {
        appointmentRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Appointment> entities) {
        appointmentRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        appointmentRepository.deleteAll();
    }

    @Override
    public <S extends Appointment> S save(S entity) {
        return appointmentRepository.save(entity);
    }

    @Override
    public <S extends Appointment> List<S> saveAll(Iterable<S> entities) {
        return appointmentRepository.saveAll(entities);
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

}

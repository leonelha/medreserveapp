package doctorsArchitecture.infrastructure.persistence;

import doctorsArchitecture.domain.models.Doctor;
import doctorsArchitecture.domain.repositories.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class DoctorRepository implements IDoctorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findAllById(Iterable<Long> ids) {
        return doctorRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return doctorRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public void delete(Doctor entity) {
        doctorRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Doctor> entities) {
        doctorRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    @Override
    public <S extends Doctor> S save(S entity) {
        return doctorRepository.save(entity);
    }

    @Override
    public <S extends Doctor> List<S> saveAll(Iterable<S> entities) {
        return doctorRepository.saveAll(entities);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

}

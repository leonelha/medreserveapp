package clientsArchitecture.infrastructure.persistence;

import clientsArchitecture.domain.models.Client;
import clientsArchitecture.domain.repositories.IClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class ClientRepository implements IClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findAllById(Iterable<Long> ids) {
        return clientRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return clientRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void delete(Client entity) {
        clientRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Client> entities) {
        clientRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public <S extends Client> S save(S entity) {
        return clientRepository.save(entity);
    }

    @Override
    public <S extends Client> List<S> saveAll(Iterable<S> entities) {
        return clientRepository.saveAll(entities);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
}

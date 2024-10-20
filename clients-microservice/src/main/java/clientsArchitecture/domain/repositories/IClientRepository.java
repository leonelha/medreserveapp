package clientsArchitecture.domain.repositories;

import clientsArchitecture.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}

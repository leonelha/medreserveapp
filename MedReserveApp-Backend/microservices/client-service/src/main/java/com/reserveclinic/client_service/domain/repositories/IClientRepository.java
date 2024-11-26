package com.reserveclinic.client_service.domain.repositories;

import com.reserveclinic.client_service.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long>{

}

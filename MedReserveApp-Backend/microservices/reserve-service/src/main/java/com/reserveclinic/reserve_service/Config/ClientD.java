package com.reserveclinic.reserve_service.Config;

import com.reserveclinic.reserve_service.application.services.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service", url = "localhost:8081")
public interface ClientD {

    @GetMapping("/api/client/search/{idAppointment}")
    ClientDTO findClientByAppointmentId(@PathVariable Long idAppointment);
}
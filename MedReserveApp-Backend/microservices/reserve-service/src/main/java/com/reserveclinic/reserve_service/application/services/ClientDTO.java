package com.reserveclinic.reserve_service.application.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String address;
}

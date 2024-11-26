package com.reserveclinic.reserve_service.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;
    @Column(name = "notes", length = 500)
    private String notes;
    @Column(name = "client_id")
    private Long clientId;
}
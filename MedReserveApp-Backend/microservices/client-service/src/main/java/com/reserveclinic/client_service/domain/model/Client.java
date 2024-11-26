package com.reserveclinic.client_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name")
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;

    public Client() {}

    public Client(ClientBuilder clientBuilder) {
        this.firstName = clientBuilder.firstName;
        this.lastName = clientBuilder.lastName;
        this.userName = clientBuilder.userName;
        this.email = clientBuilder.email;
        this.phone = clientBuilder.phone;
        this.address = clientBuilder.address;
        this.password = clientBuilder.password;
    }

    public static class ClientBuilder {
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String phone;
        private String address;
        private String password;

        public ClientBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ClientBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ClientBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public ClientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ClientBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public ClientBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Client build() {
            return new Client(this);
        }

    }
}

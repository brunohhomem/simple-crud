package com.challenge.simplecrud.domain.clients;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "clients")
@Entity(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String phone;

    public Clients(RequestClientsDTO requestClient) {
        this.name = requestClient.name();
        this.email = requestClient.email();
        this.phone = requestClient.phone();
    }
}

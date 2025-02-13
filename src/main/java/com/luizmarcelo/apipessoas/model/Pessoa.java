package com.luizmarcelo.apipessoas.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pessoa extends BaseEntity {

    private String nome;

    private String email;

}

package com.luizmarcelo.apipessoas.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Mensagem extends BaseEntity {

    private String assunto;

    private String conteudo;

    private boolean urgencia;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pessoa> pessoas;

}

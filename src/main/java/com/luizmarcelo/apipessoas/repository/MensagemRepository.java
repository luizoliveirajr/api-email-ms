package com.luizmarcelo.apipessoas.repository;

import com.luizmarcelo.apipessoas.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
}
package com.luizmarcelo.apipessoas.repository;

import com.luizmarcelo.apipessoas.model.EmailEnvio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailEnvioRepository extends JpaRepository<EmailEnvio, Integer> {
}
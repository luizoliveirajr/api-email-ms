package com.luizmarcelo.apipessoas.repository;

import com.luizmarcelo.apipessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
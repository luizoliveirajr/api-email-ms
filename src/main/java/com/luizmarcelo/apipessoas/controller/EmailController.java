package com.luizmarcelo.apipessoas.controller;

import com.luizmarcelo.apipessoas.dto.EmailEnvioRequestDTO;
import com.luizmarcelo.apipessoas.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> cadastrarEmail(@RequestBody EmailEnvioRequestDTO dto){
        this.emailService.cadastrarEmail(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

# Microsserviço de envio de email
Este projeto tem a finalidade de cadastrar usuarios e enviar emails aos mesmos.
## Tecnologias Utilizadas

- ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
- ![RabbitMQ](https://img.shields.io/badge/rabbitmq-%23FF6600.svg?&style=for-the-badge&logo=rabbitmq&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
- Flyway - para controle de migrations.
- SMTP do Google.
- 
  ## Funcionalidades
- App:
  - cadastro de usuarios e mensagens.
  - Envio de emails a partir de templates na aplicação.
  - profiles para deploy(homolog/produção).
  
## Como Executar o Projeto

1. **SpringBoot 3**:
   - Clone este repositório.
   - Importe o projeto em sua IDE favorita.
   - Coloque seu email e senha do SMTP, no application.yml
   - Execute a aplicação Spring Boot.

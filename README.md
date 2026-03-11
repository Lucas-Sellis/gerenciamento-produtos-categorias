![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23C5E413?style=for-the-badge&logo=swagger&logoColor=black)


📦 Gerenciamento de Produtos e Categorias
Este é um projeto de Backend robusto focado no gerenciamento de produtos e suas categorias, aplicando as melhores práticas de desenvolvimento Java moderno, Clean Code e arquitetura em camadas.

🚀 Tecnologias Utilizadas
Java 17 & Spring Boot 3

Spring Data JPA: Persistência de dados e consultas ao banco.

PostgreSQL: Banco de dados relacional.

MapStruct: Mapeamento inteligente entre Entidades e DTOs.

Lombok: Redução de código boilerplate.

Docker & Docker Compose: Containerização de toda a aplicação e banco de dados.

Swagger/OpenAPI: Documentação interativa dos Endpoints.

🛠️ Funcionalidades
CRUD Completo de Categorias: Cadastro, listagem, atualização e remoção.

CRUD de Produtos: Vinculação obrigatória com categorias existentes.

Tratamento de Exceções: Sistema personalizado de erros (404 Not Found, 409 Conflict).

Validação de Dados: Uso de @Valid para garantir a integridade das informações.

Documentação: API totalmente testável via Swagger UI.

🏗️ Estrutura do Projeto
O projeto segue a arquitetura em camadas para facilitar a manutenção e escalabilidade:

controller: Porta de entrada da API (REST).

business.service: Onde residem as regras de negócio.

business.dto: Objetos de transferência de dados (Segurança e Performance).

infrastructure.entity: Representação das tabelas do banco de dados.

infrastructure.repository: Interface de comunicação com o banco (JPA).

🐳 Como Executar com Docker
Para rodar o projeto completo (API + Banco de Dados) com apenas um comando, você precisa ter o Docker instalado e usar:

Bash
docker-compose up -d
A API estará disponível em: http://localhost:8080

📖 Documentação da API
Após subir o projeto, acesse o Swagger para testar os endpoints:
http://localhost:8080/swagger-ui/index.html

👨‍💻 Autor
Lucas Sellis
LinkedIn | GitHub
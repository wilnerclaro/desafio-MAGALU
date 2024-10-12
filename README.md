# desafio-MAGALU

# Projeto de Serviço de Comunicação

## Visão Geral

Este projeto é um serviço de agendamento de comunicações construído em Java e Spring Boot. Ele fornece APIs REST para
agendar, buscar e cancelar tarefas de comunicação. As comunicações podem ser agendadas utilizando diferentes canais,
como EMAIL, SMS, etc., e são armazenadas em um banco de dados relacional.

## Funcionalidades

- Agendar uma comunicação para ser enviada em um horário especificado.
- Buscar detalhes de uma comunicação agendada pelo ID.
- Cancelar uma comunicação agendada.

## Tecnologias Utilizadas

- **Java 11**: Linguagem de programação principal utilizada no desenvolvimento.
- **Spring Boot**: Framework para construir a API REST e lidar com injeção de dependências.
- **Spring Data JPA**: Para interagir com o banco de dados relacional e gerenciar entidades.
- **Lombok**: Para reduzir código boilerplate, como getters, setters e construtores.
- **H2 Database** (para testes): Banco de dados em memória para facilitar testes locais.
- **PostgreSQL** : Banco de dados responsável por persistir os dados
- **Swagger** : Documentação dos end-points da aplicação
- **Mockito** : Framework responsável por auxiliar na criação e execução dos testes unitários
- **JUnit5** : Framework responsável por auxiliar na criação e execução dos testes unitários
- **Jacocoo** : Framework responsável por gerar relatório de cobertura de testes do código

## Pré-requisitos

- **Java 11** ou superior.
- **Maven**: Para construir o projeto.
- **PostgreSQL**: Para armazenar dados de comunicação (ou usar H2 para testes).

## Instruções de Configuração

### 1. Clonar o Repositório

```bash
git clone https://github.com/wilnerclaro/desafio-MAGALU.git
```


### 2. Configurar a Aplicação

A aplicação possui 2 profiles **dev** e **test**, onde o profile dev utiliza o banco de dados **PostgreSQL** e **test**
utiliza o banco em memoria H2.
Atualize o arquivo `application.properties` com o profile desejado :

```properties
# Configuração do profile
spring.profiles.active=${APP_PROFILE:test}
spring.jpa.open-in-view=false
```

```properties
# Configuração do Banco de Dados
spring.application.name=comunicacao-api
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_schema
spring.datasource.username=seu_usuario_db
spring.datasource.password=sua_senha_db
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=update
```

## Endpoints da API

Apos iniciar a aplicação ,
você pode ver a documentação da API a partir do seguinte caminho:

```bash
http://localhost:8080/swagger-ui/index.html
```

- **Agendar Comunicação**
    - **POST** `/api/communication/new`
    - **Corpo da Requisição**:
      ```json
      {
        "scheduleTime": "2024-10-10T20:47:24.699Z",
        "message": "Sua mensagem aqui",
        "recipient": "recipient@example.com",
        "channel": "EMAIL"
      }
      ```

- **Buscar Comunicação Agendada pelo ID**
    - **GET** `/api/communication/findSchedulle/{schedulleId}`

- **Cancelar Comunicação Agendada**
    - **PATCH** `/api/communication/cancel/{schedulleId}`

## Testes

Testes unitários estão incluídos no projeto

## Melhorias Futuras

- Adicionar integração completa com RabbitMQ.
- Criar uma imagem docker da API

## Licença

Este projeto está licenciado sob a licença MIT.

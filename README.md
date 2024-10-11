# desafio-MAGALU

# Projeto de Servi�o de Comunica��o

## Vis�o Geral

Este projeto � um servi�o de agendamento de comunica��es constru�do em Java e Spring Boot. Ele fornece APIs REST para
agendar, buscar e cancelar tarefas de comunica��o. As comunica��es podem ser agendadas utilizando diferentes canais,
como EMAIL, SMS, etc., e s�o armazenadas em um banco de dados relacional.

## Funcionalidades

- Agendar uma comunica��o para ser enviada em um hor�rio especificado.
- Buscar detalhes de uma comunica��o agendada pelo ID.
- Cancelar uma comunica��o agendada.

## Tecnologias Utilizadas

- **Java 11**: Linguagem de programa��o principal utilizada no desenvolvimento.
- **Spring Boot**: Framework para construir a API REST e lidar com inje��o de depend�ncias.
- **Spring Data JPA**: Para interagir com o banco de dados relacional e gerenciar entidades.
- **Lombok**: Para reduzir c�digo boilerplate, como getters, setters e construtores.
- **H2 Database** (para testes): Banco de dados em mem�ria para facilitar testes locais.
- **PostgreSQL** : Banco de dados respons�vel por persistir os dados
- **Swagger** : Documenta��o dos end-points da aplica��o
- **Mockito** : Framework respons�vel por auxiliar na cria��o e execu��o dos testes unit�rios
- **JUnit5** : Framework respons�vel por auxiliar na cria��o e execu��o dos testes unit�rios

## Pr�-requisitos

- **Java 11** ou superior.
- **Maven**: Para construir o projeto.
- **PostgreSQL**: Para armazenar dados de comunica��o (ou usar H2 para testes).

## Instru��es de Configura��o

### 1. Clonar o Reposit�rio

```bash
git clone https://github.com/wilnerclaro/desafio-MAGALU.git
```

### 2. Construir o Projeto

Use o Maven para construir o projeto:

```bash
mvn clean install
```

### 3. Configurar a Aplica��o

A aplica��o possui 2 profiles **dev** e **test**, onde o profile dev utiliza o banco de dados **PostgreSQL** e **test**
utiliza o banco em memoria H2.
Atualize o arquivo `application.properties` com o profile desejado :

```properties
# Configura��o do profile
spring.profiles.active=${APP_PROFILE:test}
spring.jpa.open-in-view=false
```

```properties
# Configura��o do Banco de Dados
spring.application.name=comunicacao-api
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_schema
spring.datasource.username=seu_usuario_db
spring.datasource.password=sua_senha_db
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=update
```

## Endpoints da API

Apos iniciar a aplica��o ,
voc� pode ver a documenta��o da API a partir do seguinte caminho:

```bash
http://localhost:8080/swagger-ui/index.html
```

- **Agendar Comunica��o**
    - **POST** `/api/communication/new`
    - **Corpo da Requisi��o**:
      ```json
      {
        "scheduleTime": "2024-10-10T20:47:24.699Z",
        "message": "Sua mensagem aqui",
        "recipient": "recipient@example.com",
        "channel": "EMAIL"
      }
      ```

- **Buscar Comunica��o Agendada pelo ID**
    - **GET** `/api/communication/findSchedulle/{schedulleId}`

- **Cancelar Comunica��o Agendada**
    - **PATCH** `/api/communication/cancel/{schedulleId}`

## Testes

Testes unit�rios est�o inclu�dos no projeto

## Melhorias Futuras

- Adicionar integra��o completa com RabbitMQ.
- Criar uma imagem docker da API

## Licen�a

Este projeto est� licenciado sob a licen�a MIT.
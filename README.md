# FórumHub - API REST

## Visão Geral

O FórumHub é um projeto de API REST com foco na replicação do processo de um fórum online. A API gerencia tópicos, permitindo que os usuários interajam com eles de forma segura e organizada.

Este projeto foi desenvolvido utilizando o framework Spring, implementando as funcionalidades de um CRUD completo (Criar, Consultar, Atualizar e Deletar) para tópicos. A persistência dos dados é realizada em um banco de dados relacional, seguindo as melhores práticas do modelo REST e com validações de regras de negócio.

## Funcionalidades Principais

A API se concentra nos tópicos e oferece as seguintes funcionalidades:

* **Criar Tópico (`POST /topicos`)**: Permite o cadastro de um novo tópico, com validação de dados e verificação de duplicidade.
* **Listar Tópicos (`GET /topicos`)**: Exibe uma listagem completa de todos os tópicos criados.
* **Detalhar Tópico (`GET /topicos/{id}`)**: Permite a visualização de um tópico específico por meio de seu ID.
* **Atualizar Tópico (`PUT /topicos/{id}`)**: Atualiza os dados de um tópico existente.
* **Excluir Tópico (`DELETE /topicos/{id}`)**: Deleta um tópico permanentemente do banco de dados.

## Regras de Negócio

Para garantir a integridade dos dados, as seguintes regras foram implementadas:

* **Validação de Campos**: Todos os campos obrigatórios (`titulo`, `mensagem`, `autor`, `curso`) devem ser preenchidos corretamente.
* **Verificação de Duplicidade**: Não é permitido o cadastro de tópicos com o mesmo `titulo` e `mensagem`.
* **Controle de Acesso**: Apenas usuários autenticados e com um token JWT válido podem interagir com a API.

## Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias e dependências:

* **Java JDK**: Versão 21.
* **Spring Boot**: Versão 3.x.
* **Maven**: Versão 4.x.
* **MySQL**: Versão 8.x.
* **Spring Data JPA**: Para abstração e persistência de dados.
* **Spring Web**: Para a construção da API REST.
* **Spring Security**: Para autenticação e autorização.
* **Flyway Migration**: Para gerenciar o versionamento do banco de dados.
* **Lombok**: Para simplificar o código com anotações.
* **Validation**: Para validação de dados.
* **JWT (JSON Web Token)**: Para a geração e validação de tokens de autenticação.

## Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter os seguintes programas instalados:

* Java JDK 17+
* Maven 4+
* MySQL 8+
* Uma IDE de sua preferência (como IntelliJ IDEA)

### Configuração do Banco de Dados

1.  Crie um banco de dados MySQL com o nome `fhub_db`.
2.  Atualize as credenciais de acesso no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost/fhub_db
    spring.datasource.username=root
    spring.datasource.password=root
    ```

### Executando a Aplicação

1.  Navegue até o diretório raiz do projeto no seu terminal.
2.  Execute o comando para construir e instalar as dependências:
    ```bash
    mvn clean install
    ```
3.  Execute a aplicação com o Spring Boot:
    ```bash
    mvn spring-boot:run
    ```
4.  A API estará disponível em `http://localhost:8080`.

## Endpoints da API

Todos os endpoints, exceto o de login, exigem autenticação via token JWT.

| Método | URI | Descrição |
| :--- | :--- | :--- |
| `POST` | `/login` | Autenticação do usuário e obtenção do token JWT. |
| `POST` | `/topicos` | Cadastra um novo tópico. |
| `GET` | `/topicos` | Lista todos os tópicos. |
| `GET` | `/topicos/{id}` | Detalha um tópico específico. |
| `PUT` | `/topicos/{id}` | Atualiza um tópico existente. |
| `DELETE` | `/topicos/{id}` | Exclui um tópico. |

### Exemplo de Uso (com Postman ou Insomnia)

#### 1. Obter o Token de Acesso

**Endpoint**: `POST http://localhost:8080/login`

**Body (JSON)**:
```json
{
  "email": "usuario@example.com",
  "senha": "suasenha123"
}
```
O retorno será o token JWT em formato de texto.

2. Utilizar o Token para Acessar Endpoints Protegidos
Após obter o token, adicione-o no cabeçalho ```Authorization``` de todas as requisições subsequentes:

Header: ```Authorization: Bearer <seu_token_aqui>```

3. Exemplo de Requisição de Criação de Tópico
Endpoint: ```POST http://localhost:8080/topicos```

Header: ```Authorization: Bearer <seu_token_aqui>```

Body (JSON):
```{
  "titulo": "Dúvida sobre Spring Security",
  "mensagem": "Como configurar o filtro de segurança?",
  "autor": {
    "id": 1
  },
  "curso": "Spring Framework"
}
```

## Estrutura do Projeto ##
```src/main/java/com/example/Fhub```

```controle/```: Classes de controle REST (```TopicoController```, ```LoginController```).

```dto/```: Classes DTO para transferência de dados (DadosLogin).

```modelo/```: Entidades JPA (Topico, Usuario, Resposta).

```repositorio/```: Interfaces de repositório Spring Data JPA.

```security/```: Filtro de segurança (SecurityFilter).

```servico/```: Camada de serviço e lógica de negócio (TopicoService, TokenService).

```FhubApplication.java```: Classe principal da aplicação.

```SecurityConfigurations.java```: Configuração de segurança do Spring.

```src/main/resources```

```application.properties```: Propriedades de configuração.

```pom.xml```: Gerenciamento de dependências.

## Testes ##
Os testes da API podem ser realizados em ferramentas de testes de API como o Postman ou Insomnia.

# Serviço de Pedidos

Este é um projeto de um serviço de pedidos desenvolvido em Spring Boot.

## Descrição

O serviço de pedidos gerencia o CRUD (criação, leitura, atualização e exclusão) de pedidos, categorias de produtos, produtos e usuários.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring MVC
- Banco de dados H2 (em memória)
- Maven

## Funcionalidades

- CRUD de pedidos
- CRUD de categorias de produtos
- CRUD de produtos
- CRUD de usuários
- Validação de dados de entrada
- Manipulação de exceções
- Integração com banco de dados em memória (H2)

## Instalação e Execução

1. Certifique-se de ter o Java JDK e o Maven instalados em seu sistema.
2. Clone este repositório para o seu sistema local.
3. Navegue até o diretório raiz do projeto no terminal.
4. Execute o comando `mvn spring-boot:run` para iniciar o aplicativo Spring Boot.

## Endpoints da API

### Pedidos

- `GET /orders`: Retorna todos os pedidos.
- `GET /orders/{id}`: Retorna um pedido pelo seu ID.
- `POST /orders`: Cria um novo pedido.
- `PUT /orders/{id}`: Atualiza um pedido existente.
- `DELETE /orders/{id}`: Exclui um pedido pelo seu ID.

### Categorias de Produtos

- `GET /categories`: Retorna todas as categorias de produtos.
- `GET /categories/{id}`: Retorna uma categoria de produto pelo seu ID.
- `POST /categories`: Cria uma nova categoria de produto.
- `PUT /categories/{id}`: Atualiza uma categoria de produto existente.
- `DELETE /categories/{id}`: Exclui uma categoria de produto pelo seu ID.

### Produtos

- `GET /products`: Retorna todos os produtos.
- `GET /products/{id}`: Retorna um produto pelo seu ID.
- `POST /products`: Cria um novo produto.
- `PUT /products/{id}`: Atualiza um produto existente.
- `DELETE /products/{id}`: Exclui um produto pelo seu ID.

### Usuários

- `GET /users`: Retorna todos os usuários.
- `GET /users/{id}`: Retorna um usuário pelo seu ID.
- `POST /users`: Cria um novo usuário.
- `PUT /users/{id}`: Atualiza um usuário existente.
- `DELETE /users/{id}`: Exclui um usuário pelo seu ID.

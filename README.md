# API Java Spring - Gerenciamento de Produtos

Este projeto é uma API desenvolvida em Java Spring para gerenciamento de produtos, com funcionalidades como criação, 
leitura, atualização e exclusão (CRUD). O código está estruturado utilizando o framework Spring, e aprimorado com 
diversas melhorias, como paginação, documentação com Swagger e tratamento de exceções.

## Tecnologias Utilizadas

- Java 17
- Maven
- Spring Data
- Spring Web
- HATEOAS
- Validation
- PostgreSQL
- Swagger

## Funcionalidades Principais

- **Criação de produto**: endpoint para criar um novo produto no banco de dados.
- **Listagem de todos os produtos**: endpoint para obter uma lista paginada de todos os produtos.
- **Listagem de produtos pelo nome**: endpoint para obter uma lista de produtos pelo nome.
- **Filtragem de produtos por valor**: endpoint para buscar produtos dentro de uma faixa de valor.
- **Consulta de produto por ID**: endpoint para buscar um produto específico pelo seu ID.
- **Atualização de produto por ID**: endpoint para atualizar as informações de um produto existente.
- **Exclusão de produto por ID**: endpoint para excluir um produto do banco de dados.

## Como Utilizar

### 1. Criação de Produto

- **Endpoint**: `POST /products`
- **Descrição**: Cria um novo produto no banco de dados.
- **Parâmetros**: Requer um corpo JSON contendo os detalhes do produto.
- **Resposta de Sucesso**: Código 201 - Produto criado com sucesso.

Exemplo de requisição:

```json
{   
	"name": "Nome do Produto",   
	"value": 29.99 
}
```

### 2. Listagem de Todos os Produtos

- **Endpoint**: `GET /products`
- **Descrição**: Retorna uma lista paginada de todos os produtos.
- **Parâmetros**: Pode incluir parâmetros de paginação, como `page` e `size`.
- **Resposta de Sucesso**: Código 200 - Produtos encontrados.

### 3. Consulta de Produto por ID

- **Endpoint**: `GET /products/{id}`
- **Descrição**: Busca um produto pelo ID.
- **Parâmetros**: O ID do produto a ser consultado.
- **Resposta de Sucesso**: Código 200 - Produto encontrado.

### 4. Filtragem de Produtos por Nome

- **Endpoint**: `GET /products/filter/byName`
- **Descrição**: Busca produtos pelo nome.
- **Parâmetros**: Deve incluir uma string `name`.
- **Resposta de Sucesso**: Código 201 - Produtos encontrados.

### 5. Filtragem de Produtos por Valor

- **Endpoint**: `GET /products/filter/byValueRange`
- **Descrição**: Busca produtos dentro de uma faixa de valor.
- **Parâmetros**: Pode incluir `minValue` e `maxValue` para definir a faixa de valor.
- **Resposta de Sucesso**: Código 201 - Produtos encontrados.


### 6. Atualização de Produto por ID

- **Endpoint**: `PUT /products/{id}`
- **Descrição**: Atualiza um produto pelo ID.
- **Parâmetros**: O ID do produto a ser atualizado e um corpo JSON com os novos detalhes do produto.
- **Resposta de Sucesso**: Código 201 - Produto atualizado.

Exemplo de requisição:

```json
{   
	"name": "Novo Nome do Produto",   
	"price": 39.99 
}
```

### 7. Exclusão de Produto por ID

- **Endpoint**: `DELETE /products/{id}`
- **Descrição**: Apaga um produto pelo ID.
- **Parâmetros**: O ID do produto a ser excluído.
- **Resposta de Sucesso**: Código 200 - Produto apagado.

## Referências

Este projeto foi baseado no tutorial disponível no vídeo 
[Spring Boot 3 | Curso Completo 2023 - YouTube](https://www.youtube.com/watch?v=wlYvA2b1BWI), de Michelli Brito.

## Melhorias Adicionais

Algumas melhorias foram incorporadas ao projeto, incluindo paginação, documentação aprimorada com Swagger e um 
tratamento abrangente de exceções para garantir uma experiência robusta.



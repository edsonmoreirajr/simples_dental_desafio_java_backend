# Teste Prático Dev (Back-End Java)

Este é um teste prático para avaliar seus conhecimentos técnicos em desenvolvimento backend utilizando a linguagem Java.
O objetivo é construir uma API REST para controle de cadastro de profissionais e seus números de contato.

## Projeto

### Schema do banco de dados

#### Tabela `contatos`

Campos:

- `nome`: Varchar (Exemplo: fixo casa, celular, escritório)
- `contato`: Varchar
- `created_date`: Date
- `profissional`: Chave estrangeira com a tabela `profissionais`

#### Tabela `profissionais`

Campos:

- `nome`: Varchar
- `cargo`: Varchar (Valores possíveis: Desenvolvedor, Designer, Suporte, Tester)
- `nascimento`: Date
- `created_date`: Date

### Mapeamentos

A tabela de `contatos` deve possuir um mapeamento N para 1 com a tabela `profissionais`.

`contatos` N -> 1 `profissionais`

## Endpoints

### Contatos

- `GET /contatos`: Retorna uma lista de contatos com base nos critérios definidos nos parâmetros.
    - Params:
        - `q` - String: Filtro para buscar contatos que contenham o texto em qualquer um de seus atributos.
        - `fields` - List<String> (opcional): Quando presente, apenas os campos listados em `fields` devem ser
          retornados.

- `GET /contatos/:id`: Retorna todos os dados do contato com o ID especificado na URL.

- `POST /contatos`: Insere os dados do contato enviados no corpo da requisição no banco de dados.
    - Body: Content-type: JSON

- `PUT /contatos/:id`: Atualiza os dados do contato com o ID especificado na URL com os dados enviados no corpo da
  requisição.
    - Body: Content-type: JSON

- `DELETE /contatos/:id`: Exclui o contato com o ID especificado na URL.

### Profissionais

- `GET /profissionais`: Retorna uma lista de profissionais com base nos critérios definidos nos parâmetros.
    - Params:
        - `q` - String: Filtro para buscar profissionais que contenham o texto em qualquer um de seus atributos.
        - `fields` - List<String> (opcional): Quando presente, apenas os campos listados em `fields` devem ser
          retornados.

- `GET /profissionais/:id`: Retorna todos os dados do profissional com o ID especificado na URL.

- `POST /profissionais`: Insere os dados do profissional enviados no corpo da requisição no banco de dados.
    - Body: Content-type: JSON

- `PUT /profissionais/:id`: Atualiza os dados do profissional com o ID especificado na URL com os dados enviados no
  corpo da requisição.
    - Body: Content-type: JSON

- `DELETE /profissionais/:id`: Realiza uma exclusão lógica do registro do profissional com o ID especificado na URL.

**Importante:** A exclusão lógica deve ser implementada neste método.

## Tecnologias e boas práticas

### Tecnologias Obrigatórias

- Frameworks: Play Framework ou Spring Boot
- Banco de dados: PostgreSQL

### Tecnologias Desejáveis

- Hibernate
- JUnit
- Swagger
- Lombok
- Docker

### Boas práticas consideradas

As boas práticas listadas abaixo não são requisitos para a entrega do projeto, mas serão consideradas para entender
melhor seu grau de domínio no desenvolvimento de projetos Java.

- ReadMe
- JavaDoc
- Código Limpo
- Testes Unitários
- Padrões de projeto (MVC, Singleton, Service, Factory...)

## Instruções

- Faça um fork deste repositório.
- Implemente a API REST utilizando as tecnologias e boas práticas mencionadas.
- Quando finalizar, envie o link do seu repositório para a equipe responsável.

## Considerações Finais

Este teste prático tem como objetivo avaliar suas habilidades e conhecimentos em desenvolvimento backend utilizando
Java. Utilize as melhores práticas de codificação e busque produzir um código limpo, legível e bem-estruturado. Se tiver
alguma dúvida, sinta-se à vontade para entrar em contato com a equipe responsável.

Boa sorte e obrigado por participar do nosso teste prático!

## Instruções para executar a aplicação localmente

Ter instalado o Docker e Docker Compose. Executar os seguintes comandos via terminal na raiz do projeto:
> docker compose -f .\misc\docker\docker-compose.yml up --build

Utilizar o Postman para testar os endpoints da aplicação. Importar para ele a collection da pasta misc\postman que está
na raiz do projeto
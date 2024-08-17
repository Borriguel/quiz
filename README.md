# <p align="center"> Quiz API</p>

Este projeto é uma API construída com **Java, Spring Boot, PostgreSQL, Docker e Docker Compose.**

A API permite a criação de quizzes, perguntas e alternativas, além de permitir a resolução de quizzes por usuários
autenticados.

## Tabela de conteúdo

- [Pré-requisitos](#pré-requisitos)
- [Configuração](#configuração)
- [Iniciando a aplicação](#iniciando-a-aplicação)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Parando a aplicação](#parando-a-aplicação)
- [Solução de problemas](#solução-de-problemas)
- [Rotas da API](#rotas-da-api)
- [Contruibuição](#contribuição)

## Pré-requisitos

- Docker
- Docker compose

## Configuração

Clone o repositório:

```bash
git clone https://github.com/Borriguel/quiz.git
```

Crie um arquivo `.env` na raíz do projeto com as seguintes variáveis:

````
DB_USERNAME= seu_usuario_db
DB_PASSWORD= sua_senha_db
JWT_SECRET= seu_segredo_jwt
JWT_EXPIRATION_MS= tempo_expiracao_em_ms
````

Exemplo:

````
DB_USERNAME= admin
DB_PASSWORD= 123
JWT_SECRET= 71596D68536E50624B2B4728443F41387635732F785B6B585567624E61
JWT_EXPIRATION_MS= 86400000
````

# Iniciando a aplicação

1. Construa e inicie os contêineres:

```bash
docker compose up
```

2. A API estará disponível em <localhost:8080> e para acessar o swagger e fazer requisições através do
   navegador <localhost:8080/swagger-ui/index.html>
3. O banco de dados PostgreSQL estará acessível na porta ``5432``

## Estrutura do Projeto

- A aplicação Java roda no contêiner `app`
- O banco de dados PostgreSQL roda no contêiner `db`
- Os contêineres estão na mesma rede `quiz-net`
- Os dados do PostgreSQL são persistidos no volume `banco-dados`

## Parando a aplicação

Para parar a aplicação, use:

```bash
docker compose down
```

Para parar a aplicação e remover os volumes (isso apagará todos os dados do banco):

```bash
docker compose down -v
```

## Solução de problemas

Se encontrar problemas, verifique:

- Se todas as variáveis de ambiente no arquivo `.env` estão corretamente definidas
- Se as portas 8080 e 5432 não estão sendo usadas por outros processos em sua máquina
- Se o Docker e Docker Compose estão instalados corretamente em sua máquina. Você pode verificar isso executando:

```bash
docker --version
```

```bash
docker compose --version
```

## Rotas da API

### Auth

#### Registro de Usuário

```
POST /api/v1/auth/registro

{
  "nome": "Exemplo Usuario",
  "username": "usuario"
  "email": "usuario@exemplo.com",
  "senha": "senhaSuperSegura123"
}
```

#### Login de Usuário

```
POST /api/v1/auth/login
{
  "username": "usuario"
  "senha": "senhaSuperSegura123"
}
```

### Usuário

#### Buscar usuário pelo ID

```
GET /api/v1/usuario/{id}
```

#### Buscar todos usuários paginados

```
GET /api/v1/usuario
```

#### Excluir usuário pelo ID

```
DELETE /api/v1/usuario/{id}
```

#### Atualizar usuário pelo ID

```
PUT /api/v1/usuario/{id}
{
  "nome": "Nome Novo",
  "username": "username_novo",
  "email": "email.novo@email.com",
  "senha": "novasenha123",
  "dataNascimento": "1990-01-01"
}
```

### Quiz

#### Criar quiz

```
POST /api/v1/quiz
{
  "titulo": "Quiz de Programação",
  "descricao": "Teste seus conhecimentos em Java e Spring"
}
```

#### Buscar quiz por ID

```
GET /api/v1/quiz/{id}
```

#### Buscar todos os quizzes paginados

```
GET /api/v1/quiz
```

#### Excluir quiz por ID

```
DELETE /api/v1/quiz/{id}
```

#### Atualizar quiz por ID

```
PUT /api/v1/quiz/{id}
{
  "titulo": "Quiz de Programação Avançada",
  "descricao": "Teste seus conhecimentos em Design Patterns e Arquitetura de Software"
}
```

### Pergunta

#### Adicionar pergunta a um quiz

```
POST /api/v1/pergunta/quiz/{id}
{
  "enunciado": "Qual é a principal característica da programação orientada a objetos?",
  "dificuldade": "MEDIO"
}
```

#### Deletar pergunta por ID

```
DELETE /api/v1/pergunta/{id}
```

#### Buscar pergunta por ID

```
GET /api/v1/pergunta/{id}
```

#### Buscar todas as perguntas de um quiz:

```
GET /api/v1/pergunta/quiz/{id}
```

#### Atualizar pergunta por ID

```
PUT /api/v1/pergunta/{id}
{
  "enunciado": "Qual é o princípio fundamental da programação orientada a objetos?",
  "dificuldade": "DIFICIL"
}
```

### Alternativa

#### Adicionar alternativa a uma pergunta

```
POST /api/v1/alternativa/pergunta/{id}
{
  "proposicao": "Encapsulamento",
  "correta": true
}
```

#### Atualizar alternativa por ID

```
PUT /api/v1/alternativa/{id}
{
  "proposicao": "Herança",
  "correta": false
}
```

#### Deletar alternativa por ID

```
DELETE /api/v1/alternativa/{id}
```

### Resposta

#### Salvar resposta

```
POST /api/v1/respostas
{
  "idQuiz": 1,
  "idPergunta": 2,
  "idAlternativa": 3
}
```

#### Buscar resposta por ID

```
GET /api/v1/respostas/{id}
```

#### Deletar resposta por ID

```
DELETE /api/v1/respostas/{id}
```

#### Deletar todas as resposta de um quiz de um usuário específico

```
DELETE /api/v1/respostas/tudo/{id}?idUsuario={idUsuario}
```

### Resultado

#### Calcular o resultado do quiz de um usuário

```
GET /api/v1/resultado/quiz/1?usuarioId={id}
```

## Contribuição

Contribuições são bem-vindas! Se você deseja contribuir, por favor siga os passos abaixo:

1. Faça um fork deste repositório.
2. Crie uma nova branch: ``git checkout -b feature/nova-feature``
3. Faça suas alterações e commit: ``git commit -m 'Adiciona nova feature'``
4. Envie para o repositório remoto: ``git push origin feature/nova-feature``
5. Abra um Pull Request explicando suas alterações.

Desenvolvido com 💜 por Rodolpho Henrique
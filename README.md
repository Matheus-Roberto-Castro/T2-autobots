# API Autobots — Atividade 2  

## Tecnologias Utilizadas
- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Spring HATEOAS  
- H2 Database  
- Lombok  
- Maven  

---

## Como Executar o Projeto

### Pré-requisitos
- JDK 17 instalado  
- Maven instalado  

### Rodar o projeto

- mvn spring-boot:run

### A API ficará disponível em:

- http://localhost:8080


# Endpoints da API

##A API contém quatro módulos principais, cada um com operações CRUD completas:

/Cliente

/Documento

/Endereco

/Telefone

## Cada seção abaixo contém os endpoints e JSONs de exemplo para testes em Postman/Thunder Client.

### 1. CLIENTE
GET /cliente

Retorna todos os clientes.

GET /cliente/{id}

Retorna um cliente pelo ID.

POST /cliente

Cadastro de cliente.

Exemplo de JSON:
{
  "nome": "Nome Completo",
  "nomeSocial": "Nome Social",
  "dataNascimento": "2000-01-01",
  "dataCadastro": "2023-01-01",
  "documentos": [
    {
      "tipo": "RG",
      "numero": "123456789"
    }
  ],
  "endereco": {
    "estado": "SP",
    "cidade": "Cidade Exemplo",
    "bairro": "Centro",
    "rua": "Rua das Flores",
    "numero": "100",
    "codigoPostal": "12345-000",
    "informacoesAdicionais": "Bloco A"
  },
  "telefones": [
    {
      "ddd": "12",
      "numero": "999999999"
    }
  ]
}

PUT /cliente/{id}

Atualização de cliente.

Exemplo de JSON:
{
  "nome": "Nome Atualizado",
  "nomeSocial": "Nome Social Atualizado",
  "dataNascimento": "2000-01-01",
  "dataCadastro": "2023-01-01"
}

DELETE /cliente/{id}

Exclui o cliente especificado.

### 2. DOCUMENTO
GET /documento

Retorna todos os documentos.

GET /documento/{id}

Retorna um documento pelo ID.

POST /documento

Cadastro de documento.

Exemplo de JSON:
{
  "tipo": "CPF",
  "numero": "00011122233"
}

PUT /documento/{id}

Atualização de documento.

Exemplo de JSON:
{
  "tipo": "RG",
  "numero": "999888777"
}

DELETE /documento/{id}

Remove o documento informado.

### 3. ENDERECO
GET /endereco

Retorna todos os endereços.

GET /endereco/{id}

Retorna um endereço pelo ID.

POST /endereco

Cadastro de endereço.

Exemplo de JSON:
{
  "estado": "SP",
  "cidade": "São José dos Campos",
  "bairro": "Centro",
  "rua": "Rua Principal",
  "numero": "200",
  "codigoPostal": "12000-000",
  "informacoesAdicionais": "Casa 2"
}

PUT /endereco/{id}

Atualização de endereço.

Exemplo de JSON:
{
  "cidade": "Cidade Atualizada",
  "rua": "Nova Rua"
}

DELETE /endereco/{id}

Remove o endereço informado.

### 4. TELEFONE
GET /telefone

Retorna todos os telefones.


GET /telefone/{id}

Retorna um telefone pelo ID.


POST /telefone

Cadastro de telefone.

Exemplo de JSON:
{
  "ddd": "12",
  "numero": "988887777"
}


PUT /telefone/{id}

Atualização de telefone.

Exemplo de JSON:
{
  "ddd": "11",
  "numero": "977777777"
}


DELETE /telefone/{id}

Remove o telefone informado.


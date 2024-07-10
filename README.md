# Cadastro de clientes integrado com a API ViaCep
Repositório com as implementações do projeto de criação de uma API REST de cadastro de clientes integrada com a API [ViaCep](https://viacep.com.br/).

Esse projeto explorou as principais vantagens dos frameworks Spring e Spring Boot, são elas:

- Inversão de controle
- Injeção de dependências
- Autoconfiguração 
- Mapeamento de objetos relacionais com JPA
- Documentação da API com Swagger/OpenAPI
- Tratamento de exceções com `@ExceptionHandler`
- Recebimento de solicitações HTTP através do componente `@RestController`

## Objetivo
Esta API tem como objetivo realizar o cadastro de clientes e preencher os campos de endereço de forma automática, utilizando os dados fornecidos pela API [ViaCep](https://viacep.com.br/).

A API [ViaCep](https://viacep.com.br/) retorna dados de localização referentes ao CEP informado no URI da solicitação HTTP, permitindo o preenchimento automático dos dados de endereço do cliente com base nos resultados obtidos.

Além das operações de **inserção** e **atualização**, esta API também fornece operações de **pesquisa** e **exclusão**.

## Endpoints
Segue abaixo a descrição dos Endpoints (recursos) que essa API oferece.

### /cadastroclientes/clientes/cadastro
- **Método HTTP**: POST
- **Descrição**: Recebe os dados do cliente em um formato específico (JSON, XML, etc) e insere os dados no banco de dados.

```
Resposta (sucesso)
- Status: 200
- Corpo: "Cliente cadastrado com sucesso!"
```

### /cadastroclientes/clientes/cadastro/{cep}
- **Método HTTP**: POST
- **Descrição**: Recebe os dados do cliente em um formato específico (JSON, XML, etc) e o valor do CEP, depois realiza uma solicitação para a API [ViaCep](https://viacep.com.br/) a fim de obter informações de localização refentes ao CEP informado e, por fim, insere os dados do cliente no banco de dados.

```
Resposta (sucesso)
- Status: 200
- Corpo: "Cliente cadastrado com sucesso!"

Resposta (falha)
- Status: 404
- Corpo: "Não foram encontrados dados para o CEP informado {cep}!"
```

### /cadastroclientes/clientes/atualizacao
- **Método HTTP**: PUT
- **Descrição**: Recebe os dados do cliente em um formato específico (JSON, XML, etc) e atualiza os dados no banco de dados.

```
Resposta (sucesso)
- Status: 200
- Corpo: "Os dados do cliente foram atualizados com sucesso!"

Resposta (falha)
- Status: 404
- Corpo: "Nenhum cliente foi encontrado com os dados informados!"
```

### /cadastroclientes/clientes/atualizacao/{cep}
- **Método HTTP**: PUT
- **Descrição**: Recebe os dados do cliente em um formato específico (JSON, XML, etc) e o valor do CEP, depois realiza uma solicitação para a API [ViaCep](https://viacep.com.br/) a fim de obter informações de localização refentes ao CEP informado e, por fim, atualiza os dados do cliente no banco de dados.

```
Resposta (sucesso)
- Status: 200
- Corpo: "Os dados do cliente foram atualizados com sucesso!"

Resposta (falha)
- Status: 404
- Corpo: "Nenhum cliente foi encontrado com os dados informados!"

Resposta (falha)
- Status: 404
- Corpo: "Não foram encontrados dados para o CEP informado {cep}!"
```

### /cadastroclientes/clientes/pesquisa/todos
- **Método HTTP** : GET
- **Descrição**: Retorna os dados de todos os clientes cadastrados no sistema.

```
Resposta (sucesso)
- Status: 200
- Corpo: Lista de clientes

Resposta (sucesso)
- Status: 200
- Corpo: "Nenhum cliente cadastrado no sistema"
```

### /cadastroclientes/clientes/pesquisa?cpf={cpf}
- **Método HTTP** : GET
- **Descrição**: Retorna os dados de um cliente com base no CPF informado.

```
Resposta (sucesso)
- Status: 200
- Corpo: Dados do cliente encontrado

Resposta (falha)
- Status: 404
- Corpo: "Nenhum cliente foi encontrado com o CPF {cpf}!"
```

### /cadastroclientes/clientes/exclusao/{id}
- **Método HTTP**: DELETE
- **Descrição**: Exclui os dados de um cliente com base no ID informado.

```
Resposta (sucesso)
- Status: 200
- Corpo: "Cliente de ID {id} excluído com sucesso!"

Resposta (falha)
- Status: 404
- Corpo: "Nenhum cliente foi encontrado com o ID {id}!"
```

## Informações sobre o ambiente de desenvolvimento
- **IDE**: Eclipse
- **JDK**: 22
- **SGBD**: MySQL
- **SO**: Windows 10

## Informações adicionais
- **Autor**: Douglas Souza de Lima
- **E-mail**: [douglaslima-pro@outlook.com](mailto:douglaslima-pro@outlook.com)
- **Linkedin**: [https://www.linkedin.com/in/douglaslima-pro/](https://www.linkedin.com/in/douglaslima-pro/)
- **Desde**: 10/07/2024
- **Versão**: 1.0
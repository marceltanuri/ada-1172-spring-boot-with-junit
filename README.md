# Projeto de Agenda de Contatos

Este projeto é um exercício do módulo de Testes do **Santander Coders 2024** que visa implementar uma aplicação de gerenciamento de contatos utilizando Java, Spring Framework e testes automatizados com JUnit e Mockito.

## Objetivo

O objetivo do projeto é criar as classes e métodos necessários para passar em todos os testes definidos na classe de teste `ContatoServiceTest`.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação principal.
- **Spring Framework**: Para injeção de dependência e integração com o JPA.
- **Spring JPA**: Para persistência e acesso a banco de dados.
- **JUnit 5**: Para a criação e execução de testes unitários.
- **Mockito**: Para simulação de dependências nos testes.

## Estrutura do Projeto

O projeto possui os seguintes pacotes e classes principais:

### Pacotes

1. **`model`**:
    - Contém a classe de entidade `Contato`.

2. **`repository`**:
    - Contém o repositório `ContatosRepository` que interage com o banco de dados.

3. **`service`**:
    - Contém a classe `ContatoService`, que implementa a lógica de negócio.

4. **`test`**:
    - Contém os testes automatizados em `ContatoServiceTest`.

### Classes

#### `Contato`
Representa a entidade de contato. A classe deve incluir:

- **Atributos**:
    - `id`: Identificador único (auto-gerado).
    - `nome`: Nome do contato.
    - `email`: E-mail do contato.
    - `telefone`: Telefone do contato.

- **Validações**:
    - O nome deve ser obrigatório e não vazio.
    - O e-mail deve ser obrigatório e no formato válido.

#### `ContatosRepository`
Interface que estende `JpaRepository`, responsável por interagir com o banco de dados. Métodos necessários:

- `Optional<Contato> findByEmail(String email);`

#### `ContatoService`
Classe que contém a lógica de negócios. Métodos principais:

1. **`salvarContato(Contato contato)`**:
    - Salva um novo contato.
    - Verifica se o e-mail já está cadastrado.
    - Valida o nome e o e-mail.

2. **`buscarContatoPorId(Long id)`**:
    - Busca um contato pelo ID.

3. **`buscarContatoPorEmail(String email)`**:
    - Busca um contato pelo e-mail.

4. **`excluirContato(Long id)`**:
    - Exclui um contato pelo ID.

## Funcionalidades

A aplicação deve permitir:

- Adicionar um novo contato com validação de dados.
- Buscar contatos por ID ou e-mail.
- Excluir contatos existentes.
- Garantir que nenhum contato com e-mail duplicado seja salvo.

## Testes Automatizados

Os testes estão localizados em `ContatoServiceTest` e incluem:

1. **Teste de criação de contato**: Verifica se o contato é salvo corretamente.
2. **Teste de validação de e-mail duplicado**: Certifica-se de que contatos com e-mail já cadastrado não são permitidos.
3. **Teste de busca por ID e e-mail**: Garante que os métodos de busca retornem os contatos corretos.
4. **Teste de exclusão de contato**: Valida se a exclusão funciona conforme esperado.
5. **Teste de validação de campos**: Testa cenários de erro, como nome vazio ou e-mail inválido.

## Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/gonzaga95/ada-1172-spring-boot-with-junit
   cd ada-1172-spring-boot-with-junitO
   ```

2. **Configure o ambiente**:
    - Certifique-se de ter o JDK 21 instalado.
    - Certifique-se de que o Gradle esteja configurado.

3. **Execute os testes**:
   ```bash
   ./gradlew test
   ```

## Estrutura do Banco de Dados

A aplicação utiliza um banco de dados em memória para persistência temporária. A estrutura da tabela `contato` será criada automaticamente pelo Spring JPA.

- **Tabela `contato`**:
    - `id` (PK): Long
    - `nome`: String
    - `email`: String (único)
    - `telefone`: String


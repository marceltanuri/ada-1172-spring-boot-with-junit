# Projeto de Agenda de Contatos

Este projeto é um exercício do módulo de Testes Automatizados do **Santander Coders 2024**.

A API foi desenvolvida com Spring Boot e utiliza **Test-Driven Development (TDD)** como metodologia central, focando na criação de testes para validar as funcionalidades principais da aplicação.

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot**
- **Gradle**
- **JUnit 5**
- **Mockito**

## Funcionalidades

### Endpoints disponíveis:

A API expõe os seguintes endpoints para gerenciar contatos:

1. **Salvar contato**
    - Método: `POST`
    - URL: `/contatos/salvar`
    - Corpo da requisição:
      ```json
      {
        "nome": "João Silva",
        "email": "joao.silva@teste.com",
        "telefone": "11987654321"
      }
      ```  

2. **Buscar contato por ID**
    - Método: `GET`
    - URL: `/contatos/buscar/{id}`

3. **Buscar contato por email**
    - Método: `GET`
    - URL: `/contatos/buscar/{email}`

4. **Buscar contatos por parte do nome**
    - Método: `GET`
    - URL: `/contatos/buscar/{parte-nome}`

5. **Excluir contato**
    - Método: `DELETE`
    - URL: `/contatos/excluir/{id}`

### Validações:
- Nome não pode estar vazio.
- E-mail deve ser válido (contendo `@` e `.`).
- Não é permitido salvar contatos com emails duplicados.

---

## Testes

O projeto adota **TDD**, com testes abrangentes para classes de serviço e controller.

### Testes no Service:
Os testes para a classe `ContatoService` garantem que:
- Contatos sejam salvos corretamente.
- Não seja permitido salvar contatos com e-mail já existente.
- Contatos sejam buscados corretamente por ID, e-mail ou parte do nome.
- Contatos sejam excluídos corretamente.
- Validações de nome e email funcionem conforme esperado.

### Testes no Controller:
Os testes para a classe `ContatoController` garantem que:
- As chamadas aos endpoints sejam redirecionadas corretamente para o service.
- Respostas apropriadas sejam retornadas para as requisições.

Exemplo de teste no `ContatoControllerTest`:
```java
@Test
public void buscarContatoPorId_DeveRetornarContato() {
    when(contatoService.buscarContatoPorId(1L)).thenReturn(contato);

    Contato resultado = contatoController.buscarContatoPorId(1L);

    assertNotNull(resultado);
    assertEquals("João Silva", resultado.getNome());
}
```

---

## Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/gonzaga95/ada-1172-spring-boot-with-junit
   cd ada-1172-spring-boot-with-junit
   ```

2. **Configure o ambiente**:
    - Certifique-se de ter o JDK 21 instalado.
    - Certifique-se de que o Gradle esteja configurado.

3. **Execute os testes**:
   ```bash
   ./gradlew test
   ```


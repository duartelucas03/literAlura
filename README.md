# 📖 LiterAlura

Este é um projeto Java desenvolvido com **Spring Boot** na trilha de Back-end da Alura. O objetivo do projeto é criar uma aplicação para gerenciar uma biblioteca utilizando a API [GutenDex](https://gutendex.com/), permitindo buscar, registrar e listar livros de forma prática.

## 🔗 Funcionalidades

- **Cadastro de Livros:** Registre livros buscados pela API [GutenDex](https://gutendex.com/).
- **Listagem Personalizada:** Liste livros registrados com filtros por título, autor ou idioma.
- **Gerenciamento de Dados:** Integração com um banco de dados PostgreSQL para persistência.

## 🛠️ Tecnologias Utilizadas

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot**: Framework para criar a aplicação com facilidade e agilidade.
- **Spring Data JPA**: Gerenciamento de dados com o banco de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Maven**: Gerenciador de dependências.

## 💻 Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto localmente:

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/literAlura.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd literAlura
    ```

3. Configure as variáveis de ambiente para conexão com o PostgreSQL no arquivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

4. Execute o projeto com o Maven:
    ```bash
    mvn spring-boot:run
    ```


## 🌟 Como Contribuir

Contribuições são super bem-vindas! Se você tiver alguma ideia para melhorar o projeto ou encontrar algum problema, siga estas etapas:

1. Faça um fork do projeto.
2. Crie uma nova branch:
    ```bash
    git checkout -b feature/sua-feature
    ```
3. Faça as alterações desejadas e adicione um commit:
    ```bash
    git commit -m "Descrição da sua alteração"
    ```
4. Envie para o seu fork:
    ```bash
    git push origin feature/sua-feature
    ```
5. Abra um pull request no repositório principal.

---



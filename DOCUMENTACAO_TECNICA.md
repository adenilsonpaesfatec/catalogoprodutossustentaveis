# Documentação Técnica - Catálogo de Brinquedos

## Visão Geral do Projeto

O projeto "Catálogo de Brinquedos" é uma aplicação Java desenvolvida com o objetivo de gerenciar um catálogo de brinquedos. Ele permite realizar operações como adicionar, editar, listar e remover brinquedos, além de organizar esses itens em categorias. A arquitetura utiliza o padrão MVC, onde cada camada é responsável por uma parte da funcionalidade, promovendo uma separação clara de responsabilidades.

## Arquitetura do Sistema

A aplicação é estruturada em camadas principais:

1. **Camada de Apresentação (View)**: Constituída por templates Thymeleaf localizados em `src/main/resources/templates`. Esses templates são responsáveis por exibir a interface para o usuário final.
2. **Camada de Controle (Controller)**: Controladores que gerenciam as interações entre a camada de apresentação e a lógica de negócios. Localizada em `src/main/java/br/com/catalogodebrinquedos/controller`.
3. **Camada de Serviço (Service)**: Contém a lógica de negócios da aplicação, encapsulando as regras e operações sobre os dados. Localizada em `src/main/java/br/com/catalogodebrinquedos/service`.
4. **Camada de Acesso a Dados (Repository)**: Gerencia a comunicação com o banco de dados, realizando as operações CRUD. Localizada em `src/main/java/br/com/catalogodebrinquedos/repository`.

## Data Transfer Objects (DTOs)

Para garantir segurança e eficiência na comunicação entre as diferentes camadas e ao interagir com a camada de apresentação, utilizam-se **DTOs** (Data Transfer Objects). Esses objetos transferem apenas os dados necessários, evitando exposição de informações sensíveis. Os principais DTOs incluem:

- **BrinquedoDTO**: Representa os dados essenciais para um brinquedo, incluindo `descricao`, `categoriaId`, `marca`, `imagem`, `valor`, e `detalhes`.
- **CategoriaDTO**: Estrutura os dados para a entidade "Categoria", com atributos como `nome`, `descricao`, e `imagem`.

Os DTOs são convertidos em entidades por meio de mapeamento na camada de serviço, promovendo uma transferência de dados segura e eficiente.

## Estrutura do Banco de Dados

A aplicação é projetada para funcionar com um banco de dados relacional, onde cada entidade no código fonte corresponde a uma tabela. Abaixo, são descritas as tabelas e seus campos principais:

### Tabela: **brinquedos**

| Coluna         | Tipo        | Restrições                        | Descrição                               |
|----------------|-------------|-----------------------------------|-----------------------------------------|
| bri_id         | BIGINT      | PRIMARY KEY, AUTO_INCREMENT      | Identificador único do brinquedo        |
| bri_descricao  | VARCHAR(255)| NOT NULL, entre 5 e 255 caracteres | Descrição do brinquedo                 |
| cat_id         | BIGINT      | FOREIGN KEY (categorias)         | Referência à categoria do brinquedo     |
| bri_marca      | VARCHAR(100)| NOT NULL, até 100 caracteres     | Marca do brinquedo                      |
| bri_imagem     | MEDIUMBLOB  | Opcional                         | Imagem do brinquedo em formato binário  |
| bri_valor      | DECIMAL     | NOT NULL, valor mínimo 0.01      | Valor do brinquedo                      |
| bri_detalhes   | VARCHAR(500)| Opcional, até 500 caracteres     | Detalhes adicionais                     |

### Tabela: **categorias**

| Coluna         | Tipo        | Restrições                        | Descrição                               |
|----------------|-------------|-----------------------------------|-----------------------------------------|
| cat_id         | BIGINT      | PRIMARY KEY, AUTO_INCREMENT      | Identificador único da categoria        |
| cat_nome       | VARCHAR(50) | NOT NULL, entre 2 e 50 caracteres | Nome da categoria                       |
| cat_descricao  | VARCHAR(255)| NOT NULL, entre 5 e 255 caracteres | Descrição da categoria                 |
| cat_imagem     | MEDIUMBLOB  | Opcional                         | Imagem da categoria em formato binário  |

### Relacionamentos

- **Relacionamento entre Brinquedos e Categorias**: A coluna `cat_id` em **brinquedos** estabelece uma relação `Muitos-para-Um` com a tabela **categorias**.
- Cada categoria pode ter múltiplos brinquedos associados, enquanto cada brinquedo pertence a uma única categoria.

## Fluxo de Dados

1. **Solicitação**: O usuário acessa uma funcionalidade (ex.: adicionar brinquedo).
2. **Controle**: O controlador correspondente manipula a solicitação, interage com o serviço e, se necessário, com o repositório.
3. **Serviço**: A camada de serviço aplica as regras de negócio e realiza operações necessárias.
4. **Repositório**: Os dados são salvos ou recuperados do banco de dados.
5. **Resposta**: O controlador retorna a resposta apropriada para a visualização do usuário.

## Estrutura do Projeto

- **src/main/java**: Contém o código fonte, separado em subdiretórios para controladores, modelos, serviços e repositórios.
- **src/main/resources**: Inclui templates Thymeleaf, arquivos de configuração e recursos estáticos (imagens, estilos).
- **target**: Diretório de saída gerado após a compilação.

## Configuração do pom.xml

O arquivo `pom.xml` é essencial para gerenciar as dependências e configurações do projeto. Abaixo, alguns dos principais elementos:

- **Dependências**: 
  - `spring-boot-starter-web`: Para desenvolvimento de aplicações web.
  - `spring-boot-starter-data-jpa`: Para suporte a JPA e operações de banco de dados.
  - `spring-boot-starter-thymeleaf`: Integração com Thymeleaf para a camada de apresentação.
  - `mysql-connector-java`: Conector para banco de dados MySQL (especificar caso seja usado).
  - `spring-boot-starter-test`: Para suporte a testes unitários com JUnit.

- **Plugins**:
  - `spring-boot-maven-plugin`: Facilita o build e execução do projeto Spring Boot.
  - `maven-surefire-plugin`: Utilizado para execução dos testes durante o build.

## Considerações sobre o Banco de Dados

O banco de dados relacional utilizado permite um mapeamento direto das entidades Java para tabelas SQL. Cada atualização no modelo de dados exige migrações, que podem ser realizadas manualmente ou com ferramentas de controle de versão de banco de dados.

As credenciais e a URL do banco devem ser configuradas no arquivo `application.properties` em `src/main/resources`, com parâmetros como:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/catalogo_de_brinquedos
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

## Dependências e Configurações Adicionais

Dependências adicionais podem ser incluídas no `pom.xml` para funcionalidades específicas, como conexão a outros bancos de dados, integração com APIs, ou ferramentas de testes avançadas.

# Catálogo de Brinquedos

## Descrição do Projeto

O projeto "Catálogo de Brinquedos" é uma aplicação Java que tem como objetivo gerenciar um catálogo de brinquedos, permitindo operações como adição, atualização, exclusão e listagem de itens. O sistema foi desenvolvido para organizar e facilitar o acesso a informações de diversos brinquedos, incluindo descrição, faixa etária, preço, e disponibilidade.

## Tecnologias Utilizadas

- **Java** - Linguagem principal para desenvolvimento
- **Maven** - Gerenciador de dependências e build
- **Banco de Dados** - (Especificar aqui caso esteja utilizando, como MySQL, PostgreSQL ou H2)
- **JUnit** - Para testes unitários
- **Git** - Controle de versão

## Estrutura de Diretórios

- **src/**: Contém o código fonte da aplicação.
- **bin/**: Arquivos binários resultantes da compilação.
- **target/**: Saída do build gerado pelo Maven.
- **pom.xml**: Arquivo de configuração do Maven, definindo dependências e plugins.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- **Java JDK 11** ou superior
- **Maven** (gerenciador de dependências)
- **Banco de Dados** (dependendo da configuração do projeto)
- **Git** (opcional, para clonar o projeto)

## Instalação

1. **Clone o repositório**

   ```bash
   git clone https://github.com/seu-usuario/catalogodebrinquedos.git
   ```

2. **Navegue até o diretório do projeto**

   ```bash
   cd catalogodebrinquedos
   ```

3. **Instale as dependências**

   Execute o comando Maven para baixar e instalar as dependências:

   ```bash
   mvn install
   ```

4. **Configuração do Banco de Dados**

   Configure as credenciais do banco de dados no arquivo de configuração (especificar o arquivo, como `application.properties`, se existir).

## Execução

Para iniciar a aplicação, utilize o comando Maven:

```bash
mvn spring-boot:run
```

Ou, caso seja um projeto padrão:

```bash
java -jar target/catalogodebrinquedos.jar
```

## Funcionalidades Principais

- Adicionar novo brinquedo ao catálogo
- Editar dados de brinquedos existentes
- Remover brinquedos do catálogo
- Listar e pesquisar brinquedos com filtros específicos

## Contribuição

Sinta-se à vontade para contribuir com o projeto. Para isso, siga as diretrizes de contribuição descritas no arquivo `CONTRIBUTING.md` (a ser criado).

## Licença

Este projeto está licenciado sob a licença MIT - consulte o arquivo `LICENSE` para mais detalhes.

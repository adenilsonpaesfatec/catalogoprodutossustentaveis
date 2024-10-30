# Manual do Usuário - Catálogo de Brinquedos

## Introdução

Bem-vindo ao Catálogo de Brinquedos! Este sistema Java permite gerenciar um catálogo de brinquedos, com funcionalidades para adicionar, editar, visualizar e remover itens, além de categorização. A interface é acessada via navegador, com uma organização clara para operações de cadastro e consulta.

## Requisitos Mínimos

- **Sistema Operacional**: Windows, macOS ou Linux
- **Java**: Versão 11 ou superior
- **Banco de Dados**: Configuração conforme `application.properties`
- **Acesso**: Navegador Web (Chrome, Firefox, Safari ou Edge)

## Acesso ao Sistema

Para acessar o sistema, certifique-se de que a aplicação está configurada e que o banco de dados está ativo. Inicie a aplicação com:

```bash
mvn spring-boot:run
```
ou
```bash
java -jar target/catalogodebrinquedos.jar
```

Após o início, o sistema estará acessível em `http://localhost:8080`.

## Navegação

- **Home**: Exibe brinquedos em destaque e acesso ao menu.
- **Catálogo de Brinquedos**: Lista de todos os brinquedos.
- **Administração**: Opções para gerenciar brinquedos e categorias.
- **Sobre a equipe**: Informações sobre a equipe.

## Funcionalidades Principais

### 1. Adicionar um Brinquedo

1. Acesse **Administração > Brinquedos** e clique em "Novo Brinquedo".
2. Preencha os seguintes campos no formulário:
   - **Descrição**: Texto descritivo (5 a 255 caracteres).
   - **Categoria**: Selecione a categoria do brinquedo (obrigatório).
   - **Marca**: Nome da marca (máximo de 100 caracteres).
   - **Imagem**: Arquivo de imagem do brinquedo (em formato binário).
   - **Valor**: Preço do brinquedo (valor positivo, mínimo de 0,01).
   - **Detalhes**: Informações adicionais (até 500 caracteres).
3. Clique no botão **Salvar** para concluir.

### 2. Editar um Brinquedo

1. Na lista de brinquedos em **Administração > Brinquedos**, localize o brinquedo e clique em **Editar**.
2. Atualize os dados necessários e clique em **Salvar** para registrar as mudanças.

### 3. Remover um Brinquedo

1. No mesmo painel de administração, clique em **Remover** ao lado do brinquedo desejado.
2. Confirme a exclusão para remover o item permanentemente.

### 4. Gerenciar Categorias

1. Acesse **Administração > Gerenciar Categorias**.
2. Para adicionar uma categoria, clique em "Nova Categoria" e preencha:
   - **Nome**: Nome da categoria (entre 2 e 50 caracteres).
   - **Descrição**: Descrição da categoria (5 a 255 caracteres).
   - **Imagem**: Arquivo de imagem da categoria.
3. Clique em **Salvar** para concluir.

### 5. Visualizar Brinquedos

1. Na **Home** ou no **Catálogo de Brinquedos**, visualize a listagem completa.

## Exemplo de Fluxo de Operação

1. Inicie o sistema e navegue até **Administração > Brinquedos**.
2. Clique em **Novo Brinquedo**, preencha os dados e **Salvar**.
3. Verifique o item na listagem, edite-o se necessário, ou remova-o conforme o fluxo.

## Resolução de Problemas Comuns

- **Erro de Conexão com o Banco de Dados**: Verifique `application.properties` para confirmar as configurações de conexão.
- **Aplicação Não Inicia**: Confirme as instalações de Java e Maven.

## Suporte e Contato

Para dúvidas adicionais, consulte o administrador do sistema.

# Política de Contribuição - Catálogo de Brinquedos

Obrigado por considerar contribuir para o projeto "Catálogo de Brinquedos"! Este documento fornece diretrizes para garantir que todas as contribuições sejam consistentes, organizadas e úteis para a comunidade.

## Como Contribuir

1. **Fork o Repositório**: Crie uma cópia do repositório no seu perfil GitHub usando o botão "Fork".
2. **Clone o Repositório**: Clone o repositório para a sua máquina local para realizar alterações.
   ```bash
   git clone https://github.com/seu-usuario/catalogodebrinquedos.git
   ```
3. **Crie uma Branch**: Use uma nova branch para suas alterações, de preferência com um nome descritivo.
   ```bash
   git checkout -b nome-da-sua-branch
   ```
4. **Implemente as Mudanças**: Realize as alterações ou adições ao código seguindo as diretrizes de estilo descritas abaixo.
5. **Teste as Alterações**: Garanta que sua contribuição não quebre nenhuma funcionalidade e que os testes estejam passando.
6. **Commit e Push**: Faça commit das suas mudanças com uma mensagem clara e faça push para o repositório.
   ```bash
   git commit -m "Descrição das mudanças"
   git push origin nome-da-sua-branch
   ```
7. **Abra um Pull Request**: Solicite a integração das suas mudanças no repositório principal.

## Diretrizes para Pull Requests

- Descreva o objetivo e o contexto da mudança.
- Evite grandes pull requests com muitas mudanças não relacionadas.
- Garanta que todas as alterações sejam documentadas e que o código esteja bem comentado.
- Responda prontamente a comentários ou sugestões da equipe de revisão.

## Diretrizes de Estilo de Código

- **Java**: Utilize convenções padrão de código Java.
  - Indentação: 4 espaços (sem tabs).
  - Nome de classes em CamelCase (ex.: `BrinquedoModel`).
  - Nomes de variáveis e métodos em camelCase (ex.: `adicionarBrinquedo`).
- **Comentários**: Comente blocos de código importantes para explicar a lógica, especialmente em métodos complexos.
- **Tamanho do Método**: Mantenha os métodos concisos, focando em uma única responsabilidade.

## Testes

- Escreva testes unitários para todas as funcionalidades novas ou alteradas.
- Garanta que os testes passem localmente antes de enviar um pull request.
- Utilize JUnit para manter a consistência dos testes no projeto.

## Código de Conduta

Para manter uma comunidade saudável e acolhedora, pedimos que todos os colaboradores ajam com respeito, paciência e cooperação. Discussões construtivas são bem-vindas, mas ofensas ou comportamento inapropriado não serão tolerados.

---

Obrigado por seguir essas diretrizes e por ajudar a melhorar o projeto "Catálogo de Brinquedos"!

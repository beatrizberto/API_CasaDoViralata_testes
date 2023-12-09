# Projeto API Rest - Casa Do Viralata - Testes

Projeto final do Módulo Testes Automatizados I da Trilha Java da Ada Tech, em parceria com o BTG Pactual &lt;womandev> #btgfaztech.

Alunas:
- Beatriz Moreira Berto
- Bruna Torres
- 
## Descrição do Projeto

Implementação de testes unitários e de integração para o sistema de cadastro de usuários e animais da ONG Casa do Viralata.
O sistema foi criado para o Módulo Programação Web II do curso, utilizando Spring Boot. 
Link do projeto original: [link];

## Estrutura de testes

### Testes unitários
- UserServiceUnitTests: validam as regras de criação de usuário (nome, email, senha);
- DogServiceUnitTests: validam as regras para criação de registro dos cães
- Nos testes unitários, utilizamos Mockito para simular o acesso aos repositórios.

### Testes de Integração
- UserControllerIntegrationTests: testa a integração do registro de usuários com o banco de dados;
- DogControllerIntegrationTests: testa a integração do registro de cães com o banco de dados;
- Nos testes de integração, utilizamos o banco em memória H2 para simular os respositósios.




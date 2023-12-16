# Projeto API Rest - Casa Do Viralata - Testes unitários e de integração

Projeto final do Módulo Testes Automatizados I da Trilha Java da Ada Tech, em parceria com o BTG Pactual &lt;womandev> #btgfaztech.

### Respositórios:
API_CasaDoViralata_testes: [https://github.com/beatrizberto/API_CasaDoViralata_testes]

API_CasaDoViralata_testesEndToEnd: [[https://github.com/beatrizberto/API_CasaDoViralata_testesEndToEnd]

### Alunas:
- Beatriz Moreira Berto ([https://github.com/beatrizberto])
- Bruna Torres ([https://github.com/torresbc])
  
## Descrição do Projeto

Implementação de testes unitários e de integração para o sistema de cadastro de usuários e animais da ONG Casa do Viralata, criado para o módulo Módulo Programação Web II do curso, utilizando ferramentas do Spring Framework.
Para os testes, utilizamos Junit, Mockito, e MockMvc. A aplicação é servida por banco de dados em memória (h2).

Este repositósio é complementado por outro, onde foram implementados dois testes End-to-End de caixa preta, utilizando RestAssure, Cucumber e Gherkin.
Link do repositório de testes End-to-End: [https://github.com/beatrizberto/API_CasaDoViralata_testesEndToEnd];

## Estrutura de testes

### Testes unitários
- UserServiceUnitTests: validam as regras de criação de usuário (nome, email, senha);
- DogServiceUnitTests: validam as regras para criação de registro dos cães


### Testes de Integração
- UserControllerIntegrationTests: testa a integração do registro de usuários com o banco de dados;
- DogControllerIntegrationTests: testa a integração do registro de cães com o banco de dados;




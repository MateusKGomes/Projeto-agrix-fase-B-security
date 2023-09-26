
No projeto Agrix - Fase B, desenvolvi uma aplicação utilizando as seguintes tecnologias: Java, Spring Boot, JWT, Spring Security, Hibernate e JPA.

Além disso, criei a rota POST /persons para salvar novas pessoas no banco, integrando a API com o código previamente adquirido e testado na fase anterior. Nesta etapa, também refatorei o código desse pacote, seguindo a organização do restante da aplicação.

Implementei autenticação no projeto, configurando o Spring Security e adicionando uma rota para login que retorna um JWT. Para isso, criei a rota POST /auth/login, que valida os dados de usuário e senha, retornando um token JWT em caso de sucesso.

Adicionalmente, limitei o acesso às rotas GET /farms, /crops e /fertilizers para usuários autenticados com as roles corretas. Apenas usuários com as permissões adequadas têm acesso a essas rotas, caso contrário, será retornado o status 403.

Durante o desenvolvimento, apliquei o conhecimento do ecossistema Spring para criar as rotas da API e utilizei a injeção de dependência para conectar as camadas de controle, serviço e persistência. Implementei entidades e repositórios utilizando o Spring Data JPA para a persistência em banco de dados, incluindo buscas customizadas. Além disso, utilizei campos de data nas rotas da API e no banco de dados.

Para garantir a qualidade e o funcionamento correto da implementação, criei testes unitários com uma cobertura de código adequada.

No geral, o projeto representa uma integração bem-sucedida de diversas tecnologias e boas práticas de desenvolvimento, proporcionando uma aplicação funcional e segura.

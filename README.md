# FaltaOquê? - API

## :open_file_folder: Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── tcc/
│   │           └── faltaoque/
│   │               ├── config/
│   │               ├── controller/
│   │               ├── dto/
│   │               │   ├── request/
│   │               │   └── response/
│   │               ├── entity/
│   │               ├── enums/
│   │               ├── exception/
│   │               ├── mapper/
│   │               ├── repository/
│   │               ├── service/
│   │               └── FaltaoqueApplication.java
│   └── resources/
│       ├── db/
│       │   └── migration/
│       ├── static/
│       ├── templates/
│       └── application.yaml
└── test/
    └── java/
        └── com/
            └── tcc/
                └── faltaoque/
                    └── FaltaoqueApplicationTests.java
```

## :pushpin: Endpoints da API

| Método | Endpoint | Descrição | Autenticação |
| :---: | --- | --- | :---: |
| **POST** | `/auth/sign-up` | Criar um novo usuário | Público |
| **POST** | `/auth/login` | Realizar o login e gerar um token | Público |
| **GET** | `/users` | Listar todos os usuários | ADMIN |
| **GET** | `/users/{id}` | Buscar um usuário por id | USER, ADMIN |
| **PUT** | `/users/{id}` | Atualizar as informações de um usuário | USER, ADMIN |
| **DELETE** | `/users/{id}` | Deletar um usuário | USER, ADMIN |
| **GET** | `/users/{id}/pantries` | Listar todas as despensas de um usuário | USER, ADMIN |
| **GET** | `/pantries` | Listar todas as despensas | ADMIN |
| **GET** | `/pantries/{id}` | Buscar uma despensa por id | USER, ADMIN |
| **POST** | `/pantries` | Criar uma nova despensa | USER, ADMIN |
| **PUT** | `/pantries/{id}` | Atualizar as informações de uma despensa | USER, ADMIN |
| **DELETE** | `/pantries/{id}` | Deletar uma despensa | USER, ADMIN |

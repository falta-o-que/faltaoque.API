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

### Location – Listar, criar, editar e excluir localizações.
| Método | Endpoint | Descrição | Autenticação |
| :---: | --- | --- | :---: |
| **GET** | `/locations/{id}` | Buscar uma localização por id | 
| **PUT** | `/locations/{id}` | Atualizar uma localização |
| **DELETE** | `/locations/{id}` | Deletar uma localização |
| **GET** | `/locations` | Listar todas as localizações |
| **POST** | `/locations` | Criar uma localização |

### User – Listar, editar e excluir usuários e listar todas as despensas de um usuário.
| Método | Endpoint | Descrição | Autenticação |
| :---: | --- | --- | :---: |
| **GET** | `/users/{id}` | Buscar um usuário por id | 
| **PUT** | `/users/{id}` | Atualizar as informações de um usuário |
| **DELETE** | `/users/{id}` | Deletar um usuário |
| **GET** | `/users` | Listar todos os usuários cadastrados |
| **GET** | `/users/{id}/pantries` | Listar todas as despensas de um único usuário |

### Purchase – Criar, editar, listar e excluir compras de uma despensa.
| Método | Endpoint | Descrição | Autenticação |
| :---: | --- | --- | :---: |
| **GET** | `/purchases/{id}` | Buscar compras por id | 
| **PUT** | `/purchases/{id}` | Atualizar as informações de uma compra |
| **DELETE** | `/purchases/{id}` | Deletar uma compra |
| **GET** | `/purchases` | Listar todas as compras |
| **GET** | `/purchases/{purchaseId}/products` | Listar produtos de uma compra |
| **POST** | `/purchases` | Criar uma nova compra |

### Category – Listar, criar, editar e excluir categorias
| Método | Endpoint | Descrição | Autenticação |
| :---: | --- | --- | :---: |
| **GET** | `/categories/{id}` | Buscar uma categoria por id | 
| **PUT** | `/categories/{id}` | Atualizar as informações de uma categoria |
| **DELETE** | `/categories/{id}` | Deletar uma categoria |
| **GET** | `/categories` | Listar todas as categorias |
| **POST** | `/categories` | Criar uma nova categoria |

### Despensas – Gerenciamento das despensas do usuário autenticado
| Método | Endpoint | Descrição | Autenticação |
| :---: | --- | --- | :---: |
| **GET** | `/pantries/{id}` | Buscar uma despensa do usuário autenticado | 
| **PUT** | `/pantries/{id}` | Atualizar uma despensa do usuário autenticado |
| **DELETE** | `/pantries/{id}` | Deletar uma despensa |
| **GET** | `/pantries` | Listar todas as despensas |
| **POST** | `/pantries` | Criar uma despensa para o usuário autenticado |

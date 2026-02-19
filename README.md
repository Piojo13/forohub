# ForoHub API

API REST desarrollada con Spring Boot que permite gestionar tópicos de
un foro con autenticación basada en JWT.

Proyecto realizado como challenge backend.

------------------------------------------------------------------------

## 🚀 Tecnologías utilizadas

-   Java 17
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   Spring Security
-   JWT (java-jwt)
-   MySQL
-   Flyway
-   Maven

------------------------------------------------------------------------

## 📂 Estructura del Proyecto

src/main/java/com/proyecto/forohub
├── config/
│   ├── SecurityConfiguration.java
│   ├── JwtAuthenticationFilter.java
│   └── ErrorHandler.java
│
├── controller/
│   ├── TopicoController.java
│   └── AutenticacionController.java
│
├── dto/
│   ├── DatosRegistroTopico.java
│   ├── DatosListadoTopico.java
│   └── DatosAutenticacion.java
│
├── model/
│   ├── Topico.java
│   └── Usuario.java
│
├── repository/
│   ├── TopicoRepository.java
│   └── UsuarioRepository.java
│
└── service/
    └── TokenService.java



------------------------------------------------------------------------

## 🛠 Configuración del Proyecto

### 1️⃣ Base de Datos

Crear base de datos en MySQL:

CREATE DATABASE forohub;

------------------------------------------------------------------------

### 2️⃣ Configuración local

Crear archivo:

src/main/resources/application-dev.properties

Con tus credenciales:

spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

jwt.secret=TU_SECRET jwt.expiration=3600000

El archivo application-dev.properties está excluido del repositorio por
seguridad.

------------------------------------------------------------------------

### 3️⃣ Ejecutar la aplicación

Desde la raíz del proyecto:

mvn spring-boot:run

La API estará disponible en:

http://localhost:8080

------------------------------------------------------------------------

## 🔐 Autenticación

La API utiliza JWT.

### Registrar usuario (solo pruebas)

POST /login/registrar

Body:

{ "username": "usuario", "password": "123456" }

------------------------------------------------------------------------

### Login

POST /login

Body:

{ "username": "usuario", "password": "123456" }

Respuesta:

TOKEN_JWT

------------------------------------------------------------------------

### Usar el token

Agregar en los headers:

Authorization: Bearer TU_TOKEN

------------------------------------------------------------------------

## 📌 Endpoints de Tópicos

Todos requieren autenticación.

### Listar tópicos

GET /topicos

### Obtener detalle

GET /topicos/{id}

### Crear tópico

POST /topicos

Body:

{ "titulo": "Primer tópico", "mensaje": "Contenido", "estado":
"ABIERTO", "autor": "Cesar", "curso": "Java 17" }

### Actualizar tópico

PUT /topicos/{id}

### Eliminar tópico

DELETE /topicos/{id}

------------------------------------------------------------------------

## ✅ Funcionalidades implementadas

-   CRUD completo de tópicos
-   Validaciones con Bean Validation
-   Seguridad con Spring Security
-   Autenticación JWT
-   Protección de rutas
-   Password encriptada con BCrypt
-   Migraciones con Flyway
-   Manejo global de errores

------------------------------------------------------------------------

## 👨‍💻 Autor

Piojo13

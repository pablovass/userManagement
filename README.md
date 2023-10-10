# userManagement
microservicio para consulta y creacion de usuarios.
# User Management Microservice

Este es un microservicio desarrollado en Java con Spring Boot para la creación y consulta de usuarios, así como la gestión de la seguridad. Permite a los usuarios registrarse, iniciar sesión y consultar su información.

## Características principales

- Creación de usuarios con validación de datos.
- Autenticación y generación de tokens JWT.
- Consulta de información de usuario.
- Capa de seguridad para proteger los endpoints.
- Almacenamiento de datos de usuario en una base de datos H2 (en memoria).

## Requisitos

- Java 8 o superior.
- Gradle 8 o superior para la gestión de dependencias.
- IDE de desarrollo compatible con Spring Boot (por ejemplo, IntelliJ IDEA o Eclipse).

## Configuración

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE.
3. Personaliza la configuración de la base de datos en `application.properties`.
4. Compila y ejecuta la aplicación.

# Uso
## Documentación de API - Registro y Consulta de Usuarios

Esta documentación describe los endpoints de la API de registro y consulta de usuarios para una aplicación Java Spring Boot 2.7. La API permite la creación de nuevos usuarios y la consulta de usuarios existentes utilizando tokens JWT para la autenticación.

## Endpoints

### Registro de Usuario

- **URL:** `/sign-up`
- **Método HTTP:** `POST`
- **Descripción:** Endpoint para crear un nuevo usuario.
- **Contrato de entrada:**
    ```json
    {
        "name": "String",
        "email": "String",
        "password": "String",
        "phones": [
            {
                "number": "long",
                "citycode": "int",
                "contrycode": "String"
            }
        ]
    }
    ```
- **Validaciones:**
    - El correo debe seguir la expresión regular `(aaaaaaa@undominio.algo)`.
    - La contraseña debe seguir la expresión regular (minimo 8 o maximo 12 letras o numeros) `(a2asfGfdfdf4)`.
- **Campos opcionales:**
    - El nombre y los teléfonos son campos opcionales.
- **Respuesta exitosa:**
    - En caso de éxito, se retorna el usuario con los siguientes campos:
        ```json
        {  
        "user_created":{
            "id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
            "created": "Nov 16, 2021 12:51:43 PM",
            "lastLogin:": "Nov 16, 2021 12:51:43 PM",
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0",
            "isActive": true
          }
        }
        ```
- **Persistencia de Datos:**
    - El usuario se persiste en una base de datos utilizando Spring Data y H2.
    - La contraseña se almacena de manera encriptada.

### Consulta de Usuario

- **URL:** `/login`
- **Método HTTP:** `GET`
- **Descripción:** Endpoint para consultar la información de un usuario autenticado.
- **Contrato de entrada:**
    ```
    http://localhost:8080/api/login?token={user token}
   EJEMPLO: http://localhost:8080/api/login?token=eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTY4OTI
    ```
- **Autenticación:** Se requiere un token JWT generado en el endpoint de registro.
- **Respuesta exitosa:**
    - En caso de éxito, se retorna la información del usuario:
        ```json
        {
            "id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
            "created": "Ene 20, 2021 12:51:43 PM",
            "lastLogin": "Nov 16, 2023 12:51:43 PM",
            "token": "NuevoTokenGenerado",
            "isActive": true,
            "name": "Julio Gonzalez",
            "email": "julio@testssw.cl",
            "password": "a2asfGfdfdf4",
            "phones": [
                {
                    "number": 87650009,
                    "citycode": 7,
                    "contrycode": "25"
                }
            ]
        }
        ```
- **Notas:**
    - El token se actualiza cada vez que se realiza una consulta exitosa.

## Respuesta de Error

En caso de error en cualquiera de los endpoints, se retornará una respuesta de error en el siguiente formato:

```json
{
    "error": [
        {
            "timestamp": "Timestamp",
            "codigo": "int",
            "detail": "String"
        }
    ]
}
```
## diagrama de componentes UML
![diagrama de componentes UML](https://github.com/pablovass/userManagement/blob/main/src/main/resources/docs/userComponent.png?raw=true)


## diagrama de secuencia UML
![diagrama de secuencia UML](https://github.com/pablovass/userManagement/blob/main/src/main/resources/docs/userSequence.png?raw=true)

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu contribución.
3. Realiza tus cambios y mejoras.
4. Envía una solicitud pull a la rama principal.

## Licencia

Este proyecto es netamente academico.

## Contacto

Si tienes preguntas o sugerencias, no dudes en ponerte en contacto con Pablo  en vallejos_pablo@live.com.


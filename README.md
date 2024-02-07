# Microservicio de Gestión de Usuarios
PROYECTO 100% ACADEMICO.

Este es un microservicio desarrollado en Java con Spring Boot para la creación y recuperación de usuarios, así como la gestión de seguridad. Permite a los usuarios registrarse, iniciar sesión y acceder a su información.
  

## Características Clave
- Creación de usuarios con validación de datos.
- Autenticación y generación de tokens JWT.
- Recuperación de información de usuarios.
- Capa de seguridad para proteger los puntos finales.
- Almacenamiento de datos de usuarios en una base de datos H2 en memoria.

## Requisitos
- Java 8 o superior.
- Gradle 8 o superior para la gestión de dependencias.
- IDE de desarrollo compatible con Spring Boot (por ejemplo, IntelliJ IDEA o Eclipse).

## Configuración
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE.
3. Personaliza la configuración de la base de datos en `application.properties`.
4. Compila y ejecuta la aplicación.
5. Datos como contraseñas no sifradas se encuentran en `application.properties`.

## Uso
### Documentación de la API - Registro y Recuperación de Usuarios

Esta documentación describe los puntos finales de la API de registro y recuperación de usuarios para una aplicación Java Spring Boot 2.7. La API permite la creación de nuevos usuarios y la recuperación de usuarios existentes utilizando tokens JWT para la autenticación.

### Puntos finales
#### Registro de Usuario
- **URL:** /sign-up
- **Método HTTP:** POST
- **Descripción:** Punto final para crear un nuevo usuario.
- **Contrato de Solicitud:**
```json
{
    "name": "String",
    "email": "String",
    "password": "String",
    "phones": [
        {
            "number": "String",
            "citycode": "String",
            "contrycode": "String"
        }
    ]
}
```
- **Validaciones:**
  - El correo electrónico debe seguir la expresión regular (aaaaaaa@dominio.com).
  - La contraseña debe seguir la expresión regular (mínimo 8 o máximo 12 letras o números) (a2asfGfdfdf4).
- **Campos Opcionales:**
  - Nombre y teléfonos son campos opcionales.
- **Respuesta Exitosa:**
  En caso de éxito, se devuelve el usuario con los siguientes campos:
```json
{  
"user_created":{
    "id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
    "created": "16 de noviembre de 2021 12:51:43 PM",
    "lastLogin:": "16 de noviembre de 2021 12:51:43 PM",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0",
    "modified": "Feb 6, 2024 10:35:47 PM",
    "isActive": true
  }
}
```
- **Persistencia de Datos:**
  - El usuario se persiste en una base de datos utilizando Spring Data y H2.
  - La contraseña se almacena en forma encriptada.

#### Recuperación de Usuario
- **URL:** /login
- **Método HTTP:** GET
- **Descripción:** Punto final para recuperar información de un usuario autenticado.
- **Contrato de Solicitud:**
  `http://localhost:8080/api/login?token={token de usuario}`
  **EJEMPLO:** `http://localhost:8080/api/login?token=eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTY4OTI`
- **Autenticación:** Se requiere un token JWT generado en el punto final de registro.
- **Respuesta Exitosa:**
  En caso de éxito, se devuelve la información del usuario:
```json
{
    "id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
    "created": "20 de enero de 2021 12:51:43 PM",
    "lastLogin": "16 de noviembre de 2023 12:51:43 PM",
    "token": "NuevoTokenGenerado",
    "isActive": true,
    "modified": "Feb 6, 2024 10:36:14 PM",
    "name": "Julio González",
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
  - El token se actualiza cada vez que se realiza una recuperación exitosa.

#### Respuesta de Error
En caso de un error en cualquiera de los puntos finales, se devolverá una respuesta de error en el siguiente formato de ejemplo:
```json
{"mensaje": "El token a consultar no se encuentra en la base de datos o es incorrecto"}
```

## Diagrama de Componentes UML
![Diagrama de Componentes UML](UML_Component_Diagram.png)

## Diagrama de Secuencia UML
![Diagrama de Secuencia UML](UML_Sequence_Diagram.png)

## Contribución
Si deseas contribuir a este proyecto, sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una nueva rama para tu contribución.
3. Realiza tus cambios y mejoras.
4. Envía una solicitud de extracción a la rama principal.

## Licencia
Este proyecto es puramente académico.

## Contacto
Si tienes preguntas o sugerencias, no dudes en ponerte en contacto con Pablo en vallejos_pablo@live.com.
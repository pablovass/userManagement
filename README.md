# userManagement
microservicio, cuyo proyecto debe ser SpringBoot / Gradle, para la creación y consulta de usuarios.
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
- Maven para la gestión de dependencias.
- IDE de desarrollo compatible con Spring Boot (por ejemplo, IntelliJ IDEA o Eclipse).

## Configuración

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE.
3. Personaliza la configuración de la base de datos en `application.properties`.
4. Compila y ejecuta la aplicación.

## Uso

### Registro de usuarios

Para registrar un nuevo usuario, realiza una solicitud POST a la siguiente URL:


El cuerpo de la solicitud debe ser un JSON que cumpla con el contrato de entrada especificado en la documentación.

### Inicio de sesión

Para iniciar sesión y obtener un token JWT, realiza una solicitud POST a la siguiente URL:


Proporciona el correo y la contraseña en el cuerpo de la solicitud. El servicio generará un token JWT que deberás incluir en las futuras solicitudes para autenticarte.

### Consulta de información de usuario

Para consultar la información de un usuario autenticado, realiza una solicitud GET a la siguiente URL:


Reemplaza `{id}` con el ID del usuario que deseas consultar. Asegúrate de incluir el token JWT en la cabecera de la solicitud para la autenticación.

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu contribución.
3. Realiza tus cambios y mejoras.
4. Envía una solicitud pull a la rama principal.

## Licencia

Este proyecto se distribuye bajo la licencia [Nombre de la Licencia]. Consulta el archivo `LICENSE` para más detalles.

## Contacto

Si tienes preguntas o sugerencias, no dudes en ponerte en contacto con [tu nombre] en [tu correo electrónico].


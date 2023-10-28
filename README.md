# User Management Microservice

This is a microservice developed in Java with Spring Boot for user creation and retrieval, as well as security management. It allows users to register, log in, and access their information.

## Key Features

- User creation with data validation.
- Authentication and JWT token generation.
- User information retrieval.
- Security layer to protect endpoints.
- User data storage in an in-memory H2 database.

## Requirements

- Java 8 or higher.
- Gradle 8 or higher for dependency management.
- Development IDE compatible with Spring Boot (e.g., IntelliJ IDEA or Eclipse).

## Configuration

1. Clone this repository to your local machine.
2. Open the project in your IDE.
3. Customize the database configuration in `application.properties`.
4. Compile and run the application.

# Usage

## API Documentation - User Registration and Retrieval

This documentation describes the endpoints of the user registration and retrieval API for a Java Spring Boot 2.7 application. The API allows the creation of new users and the retrieval of existing users using JWT tokens for authentication.

## Endpoints

### User Registration

- **URL:** `/sign-up`
- **HTTP Method:** `POST`
- **Description:** Endpoint to create a new user.
- **Request Contract:**
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
- **Validations:**
    - Email must follow the regular expression `(aaaaaaa@domain.com)`.
    - Password must follow the regular expression (minimum 8 or maximum 12 letters or numbers) `(a2asfGfdfdf4)`.
- **Optional Fields:**
    - Name and phones are optional fields.
- **Successful Response:**
    - In case of success, the user is returned with the following fields:
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
- **Data Persistence:**
    - The user is persisted in a database using Spring Data and H2.
    - The password is stored in an encrypted form.

### User Retrieval

- **URL:** `/login`
- **HTTP Method:** `GET`
- **Description:** Endpoint to retrieve information of an authenticated user.
- **Request Contract:**
    ```
    http://localhost:8080/api/login?token={user token}
   EXAMPLE: http://localhost:8080/api/login?token=eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTY4OTI
    ```
- **Authentication:** A JWT token generated in the registration endpoint is required.
- **Successful Response:**
    - In case of success, the user's information is returned:
        ```json
        {
            "id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
            "created": "Jan 20, 2021 12:51:43 PM",
            "lastLogin": "Nov 16, 2023 12:51:43 PM",
            "token": "NewlyGeneratedToken",
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
- **Notes:**
    - The token is updated every time a successful retrieval is made.

## Error Response

In case of an error in any of the endpoints, an error response will be returned in the following format:

```json
{
    "error": [
        {
            "timestamp": "Timestamp",
            "code": "int",
            "detail": "String"
        }
    ]
}
```

## UML Component Diagram

![UML Component Diagram](https://github.com/pablovass/userManagement/blob/main/src/main/resources/docs/userComponent.png?raw=true)

## UML Sequence Diagram

![UML Sequence Diagram](https://github.com/pablovass/userManagement/blob/main/src/main/resources/docs/userSequence.png?raw=true)

## Contribution

If you wish to contribute to this project, follow these steps:

1. Fork the repository.
2. Create a new branch for your contribution.
3. Make your changes and improvements.
4. Send a pull request to the main branch.

## License

This project is purely academic.

## Contact

If you have questions or suggestions, don't hesitate to contact Pablo at vallejos_pablo@live.com.

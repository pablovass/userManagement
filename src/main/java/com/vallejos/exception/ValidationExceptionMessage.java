package com.vallejos.exception;

public enum ValidationExceptionMessage {
    EMPTY_OBJECT("no hay peticion hecha o no exite el campo a consultar"),
    EMAIL_INVALID("El correo electrónico no es válido"),
    PASSWORD_INVALID("La contraseña no es válida"),
    EMAIL_ALREADY_EXISTS("El correo electrónico ya existe"),
    TOKEN_INVALID("El token a consultar no se encuentra en la base de datos o es incorrecto");

    private String message;

    ValidationExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


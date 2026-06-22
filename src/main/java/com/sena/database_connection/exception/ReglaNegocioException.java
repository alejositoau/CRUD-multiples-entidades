package com.sena.database_connection.exception;

import org.springframework.http.HttpStatus;

public class ReglaNegocioException extends RuntimeException {
    
    private final HttpStatus status;

    public ReglaNegocioException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST; // Por defecto mandamos 400
    }

    public ReglaNegocioException(String message, HttpStatus status) {
        super(message);
        this.status = status; // Para mandar 409 Conflict cuando hay cruces
    }

    public HttpStatus getStatus() {
        return status;
    }
}
package br.com.alura.microservices.store.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException() {
        this("Resource not found");
    }

    public ResourceNotFoundException(final String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}

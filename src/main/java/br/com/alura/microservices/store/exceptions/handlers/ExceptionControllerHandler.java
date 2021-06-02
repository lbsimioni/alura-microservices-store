package br.com.alura.microservices.store.exceptions.handlers;

import br.com.alura.microservices.store.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> exception(final Exception e, final HttpServletRequest request) {
        log(e, request);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Void> baseException(final HttpClientErrorException.NotFound e, final HttpServletRequest request) {
        log(e, request);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Void> baseException(final BaseException e, final HttpServletRequest request) {
        log(e, request);
        return ResponseEntity.status(e.getStatus()).build();
    }

    private void log(final Exception e, final HttpServletRequest request) {
        log.error("Error: " + e.getMessage() + " in " + request.getMethod() + " " + request.getServletPath());
    }
}
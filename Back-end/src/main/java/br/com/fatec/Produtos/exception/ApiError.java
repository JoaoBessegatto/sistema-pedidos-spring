package br.com.fatec.Produtos.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
    private int status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

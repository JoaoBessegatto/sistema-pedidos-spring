package br.com.fatec.Produtos.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}

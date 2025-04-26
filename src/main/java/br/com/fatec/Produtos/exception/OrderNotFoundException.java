package br.com.fatec.Produtos.exception;

public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException(String message) {
        super(message,404);
    }
}

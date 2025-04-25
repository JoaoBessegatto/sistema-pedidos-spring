package br.com.fatec.Produtos.exception;

public class InvalidProdutoException extends BaseException {
    public InvalidProdutoException(String message) {
        super(message, 400);
    }
}

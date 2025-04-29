package br.com.fatec.Produtos.exception;

public class ProdutoNotFoundException extends BaseException {
    public ProdutoNotFoundException(String message) {
        super(message, 404);
    }
}

package br.com.fatec.Produtos.exception;

public class EstoqueInsuficienteException extends BaseException{
    public EstoqueInsuficienteException(String message) {
        super(message, 409);
    }
}

package br.com.rafaelcarvalho.libraryapi.api.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}

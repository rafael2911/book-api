package br.com.rafaelcarvalho.libraryapi.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors erroDeValidacaoDeFormulario(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();

        return new ApiErrors(result);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors erroBusinessException(BusinessException ex){
        return new ApiErrors(ex.getMessage());
    }

}

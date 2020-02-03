package br.com.rafaelcarvalho.libraryapi.api.exception;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(BindingResult result) {
        errors = new ArrayList<>();
        result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
    }

    public ApiErrors(String message) {
        this.errors = Arrays.asList(message);
    }

    public List<String> getErrors() {
        return errors;
    }
}

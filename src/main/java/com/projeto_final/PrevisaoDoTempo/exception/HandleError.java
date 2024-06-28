package com.projeto_final.PrevisaoDoTempo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandleError {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleErrorIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(CityNotFind.class)
    public ResponseEntity<String> handleErrorFindCityException(CityNotFind e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(FindCityException.class)
    public ResponseEntity<String> handleErrorFindCityException(FindCityException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ApiErroDetalhe> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()//FIFO => estrutura de dados
                .map(p -> new ApiErroDetalhe(p.getField(), p.getDefaultMessage())).toList();

        final String mensagemErroValidacao = "Erro na validação";
        final ApiErro apiErro = new ApiErro(mensagemErroValidacao, errors);
        return ResponseEntity.badRequest().body(apiErro);
    }
}
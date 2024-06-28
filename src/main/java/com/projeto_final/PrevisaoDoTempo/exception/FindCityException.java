package com.projeto_final.PrevisaoDoTempo.exception;

public class FindCityException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public FindCityException(String ex){
        super(ex);
    }
}

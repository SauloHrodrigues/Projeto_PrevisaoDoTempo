package com.projeto_final.PrevisaoDoTempo.exception;

public class CityNotFind extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public CityNotFind(String message) {
        super(message);
    }
}

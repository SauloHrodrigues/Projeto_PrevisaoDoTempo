package com.projeto_final.PrevisaoDoTempo.exception;

public class MeteorologicalDataNotFind extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public MeteorologicalDataNotFind(String message) {
        super(message);
    }
}

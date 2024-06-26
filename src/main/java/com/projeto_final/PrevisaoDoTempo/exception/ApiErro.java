package com.projeto_final.PrevisaoDoTempo.exception;

import java.util.List;

public record ApiErro(String mensagem, List<ApiErroDetalhe> erros) {
}

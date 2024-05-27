package com.projeto_final.PrevisaoDoTempo.mapper;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;

public class MapperCidade {

    public static Cidade dtoToEntity(CidadeRequestDdo dados){
        Cidade novaCidade = new Cidade();
        novaCidade.setNome(dados.getNome());
        return novaCidade;
    }

    public static CidadeResponseDto entityToResponseDto(Cidade cidade) {
        CidadeResponseDto responseDto = new CidadeResponseDto();
        responseDto.setId(cidade.getId());
        responseDto.setNome(cidade.getNome());
        responseDto.setDadosMeteorologicos(cidade.getDadosMeteorologicos());
        return responseDto;
    }
}

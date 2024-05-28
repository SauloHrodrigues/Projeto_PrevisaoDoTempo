package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperDadosMetearologicos;

public class CidadeRequestDtoFixture {

    public static CidadeRequestDdo gerarCidadeRequestDto(String nomeDaCidade){
        CidadeRequestDdo cidadeDto = new CidadeRequestDdo();
        DadoMeteorologico dadoMeteorologico = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        DadoMeteorologicoResponseDto reponse = MapperDadosMetearologicos.entityToResponseDto(dadoMeteorologico);
        cidadeDto.setNome(nomeDaCidade);
        return cidadeDto;
    }
    public static CidadeRequestDdo  gerarCidadeRequestDtoComDadosMeteorologico(String nomeDaCidade){
        CidadeRequestDdo cidadeDto = new CidadeRequestDdo();
        DadoMeteorologico dadoMeteorologico = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        DadoMeteorologicoResponseDto reponse = MapperDadosMetearologicos.entityToResponseDto(dadoMeteorologico);
        cidadeDto.setNome(nomeDaCidade);
        cidadeDto.setDadosMeteorologicos(reponse);
        return cidadeDto;
    }
}

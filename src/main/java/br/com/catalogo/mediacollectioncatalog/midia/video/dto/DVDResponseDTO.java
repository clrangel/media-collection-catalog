package br.com.catalogo.mediacollectioncatalog.midia.video.dto;

import java.util.List;

public record DVDResponseDTO(

        Long id,
        String titulo,
        Integer anoLancamento,

        List<String> diretores,

        String roteiro,
        String sinopse,
        String atores,
        String genero,
        String paisFilme,

        String tipoMidia,
        String categoriaVideo,
        String tipoEdicao,
        Integer numeroDiscos

) {}

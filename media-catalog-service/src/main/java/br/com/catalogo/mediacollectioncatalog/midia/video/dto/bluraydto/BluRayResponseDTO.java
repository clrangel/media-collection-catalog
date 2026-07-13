package br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto;

import java.util.List;

public record BluRayResponseDTO(


        Long id,
        String titulo,
        String tituloOriginal,
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
        Integer numeroDiscos,
        String resolucao
) {}

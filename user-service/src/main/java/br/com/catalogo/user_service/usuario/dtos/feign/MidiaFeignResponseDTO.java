package br.com.catalogo.user_service.usuario.dtos.feign;

public record MidiaFeignResponseDTO(

        String titulo,
        Integer anoLancamento,
        String tipoMidia,
        String artistaOuDiretor

) {}

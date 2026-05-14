package br.com.catalogo.mediacollectioncatalog.midia.video.dto.dvddto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record DVDRequestDTO(

        @Schema(description = "Título do DVD", example = "Os Bons Companheiros")
        String titulo,

        @Schema(description = "Título original do filme", example = "Goodfellas")
        String tituloOriginal,

        @Schema(description = "Ano de lançamento", example = "1990")
        Integer anoLancamento,


        @Schema(description = "História do Filme", example = "Henry Hill (Ray Liotta) conta a sua história de garoto do Brooklyn, Nova York...")
        String sinopse,

        @Schema(description = "Atores presentes no Filme", example = "Robert De Niro, Ray Liotta")
        String atores,

        @Schema(description = "Roteiristas do Filme", example = "Martin Scorsese")
        String roteiro,

        @Schema(
                description = "Gênero cinematográfico do filme",
                allowableValues = {
                        "ACAO",
                        "AVENTURA",
                        "COMEDIA",
                        "DRAMA",
                        "ROMANCE",
                        "TERROR",
                        "SUSPENSE",
                        "FICCAO_CIENTIFICA",
                        "FANTASIA",
                        "ANIMACAO",
                        "DOCUMENTARIO",
                        "BIOGRAFIA",
                        "GUERRA",
                        "POLICIAL",
                        "MISTERIO",
                        "MUSICAL",
                        "ESPORTE",
                        "FAROESTE",
                        "HISTORICO",
                        "CRIME"
                },
                example = "DRAMA"
        )
        String genero,

        @Schema(
                description = "Categoria do Filme",
                allowableValues = {
                        "FILME",
                        "SERIE",
                        "DOCUMENTARIO",
                        "SHOWS",
                        "MINISSERIE",
                        "NOVELA"

                },
                example = "FILME"
        )
        String categoriaVideo,

        @Schema(description = "País de origem do Filme", example = "Viva Emptiness")
        String paisFilme,

        @Schema(description = "IDs dos diretores", example = "1")
        List<Long> diretoresIds,


        @Schema(description = "Número de discos da edição", example = "1")
        Integer numeroDiscos,

        @Schema(
                description = "Tipo físico do DVD",
                allowableValues = {
                        "SIMPLES","DUPLO","TRIPLO","BOX"
                },
                example = "SIMPLES"
        )
        String tipoEdicao

        ) {}

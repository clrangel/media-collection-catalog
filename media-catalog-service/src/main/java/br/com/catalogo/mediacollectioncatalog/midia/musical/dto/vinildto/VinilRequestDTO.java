package br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto;

import io.swagger.v3.oas.annotations.media.Schema;

public record VinilRequestDTO(

        @Schema(description = "Título do Vinil", example = "Viva Emptiness")
        String titulo,

        @Schema(description = "Ano de lançamento", example = "2003")
        Integer anoLancamento,

        @Schema(
                description = "Gênero musical",
                allowableValues = {
                        "ALTERNATIVE_ROCK","AMBIENT","BARROCO","BLACK_METAL","BLUES",
                        "CLASSICO","DEATH_METAL","DISCO","DOOM_METAL","ELETRONICA",
                        "FUNK","GOTHIC","GOTHIC_METAL","GROOVE_METAL","GRUNGE",
                        "HARD_ROCK","HEAVY_METAL","HIP_HOP","HOUSE","INDIE",
                        "JAZZ","METAL","MUSICA_BRASILEIRA","OPERA","POP",
                        "POP_ROCK","POWER_METAL","PROGRESSIVE_METAL","PROGRESSIVE_ROCK",
                        "PUNK","RAP","REGGAE","RNB","ROCK","ROMANTICO",
                        "SKA","SOUL","SYMPHONIC_METAL","SYNTH_POP","TECHNO",
                        "THRASH_METAL","TRANCE"
                },
                example = "ROCK"
        )
        String genero,

        @Schema(
                description = "Categoria do disco",
                allowableValues = {"ALBUM","EP","SINGLE","COLETANEA","AO_VIVO"},
                example = "ALBUM"
        )
        String categoria,

        @Schema(description = "Descrição do Vinil", example = "Viva Emptiness é o sexto álbum de estúdio da banda.")
        String descricao,

        @Schema(description = "ID do artista", example = "1")
        Long artistaId,

        @Schema(description = "Número de discos", example = "1")
        Integer numeroDiscos,

        @Schema(
                description = "Formato do disco",
                allowableValues = {"SIMPLES","DUPLO","TRIPLO","BOX","DELUXE"},
                example = "SIMPLES"
        )
        String formatoDisco,

        @Schema(
                description = "Tipo físico do Vinil",
                allowableValues = {
                        "PICTURE_DISC",
                        "COLORED",
                        "TRANSPARENT",
                        "STANDARD_BLACK",
                        "GATEFOLD",
                        "LIQUID_FILLED",
                        "FLEXI_DISC",
                        "ACETATE",
                        "PROMO",
                        "COMPACTO",
                        "BOX_SET",
                        "OUTRO"
                },
                example = "STANDARD_BLACK"
        )
        String tipoVinil,

        @Schema(
                description = "Lista de faixas do Vinil (uma por linha ou numeradas)",
                example = "1. Intro\n2. Track One\n3. Track Two"
        )
        String faixasTexto
) {}

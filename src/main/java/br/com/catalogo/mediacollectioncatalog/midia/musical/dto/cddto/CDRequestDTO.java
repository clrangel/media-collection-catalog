package br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CDRequestDTO(

        @Schema(description = "Título do CD", example = "Viva Emptiness")
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

        @Schema(description = "Descrição do CD", example = "Viva Emptiness é o sexto álbum de estúdio da banda.")
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
                description = "Tipo físico do CD",
                allowableValues = {
                        "JEWEL_CASE","FATBOX","DIGIPAK","DIGISLEEVE","DIGISTAK",
                        "SLIPCASE","CARDBOARD_SLEEVE","BOX_SET","STEELBOOK",
                        "MINI_LP","OUTRO"
                },
                example = "JEWEL_CASE"
        )
        String tipoCD
) {}

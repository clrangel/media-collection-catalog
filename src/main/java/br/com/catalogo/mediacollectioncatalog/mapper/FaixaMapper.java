package br.com.catalogo.mediacollectioncatalog.mapper;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaDetalhadaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class FaixaMapper {

    public FaixaDetalhadaResponseDTO toDetalhadoDTO(Faixa faixa) {
        return new FaixaDetalhadaResponseDTO(
                faixa.getNumero(),
                faixa.getTitulo(),
                faixa.getMidiaMusical().getTitulo(),
                faixa.getMidiaMusical().getArtista().getNome()
        );
    }
}

package br.com.catalogo.mediacollectioncatalog.midia;

import br.com.catalogo.mediacollectioncatalog.midia.dtos.MidiaBuscaDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import br.com.catalogo.mediacollectioncatalog.midia.video.domain.MidiaVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MidiaService {

    private final MidiaRepository repository;


    public List<MidiaBuscaDTO> buscarPorTitulo(String titulo) {

        return repository.buscarPorTitulo(titulo)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private MidiaBuscaDTO toDTO(Midia midia) {

        String tipoMidia = midia.getClass().getSimpleName().toUpperCase();

        String artistaOuDiretor;

        if (midia instanceof MidiaMusical musical) {

            artistaOuDiretor = musical.getArtista().getNome();

        } else if (midia instanceof MidiaVideo video) {

            artistaOuDiretor = video.getDiretores()
                    .stream()
                    .map(d -> d.getNome())
                    .findFirst()
                    .orElse("Desconhecido");

        } else {

            artistaOuDiretor = "Desconhecido";
        }

        return new MidiaBuscaDTO(
                midia.getTitulo(),
                tipoMidia,
                artistaOuDiretor
        );
    }
}

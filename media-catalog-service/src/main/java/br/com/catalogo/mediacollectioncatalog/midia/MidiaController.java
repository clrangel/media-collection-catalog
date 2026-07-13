package br.com.catalogo.mediacollectioncatalog.midia;

import br.com.catalogo.mediacollectioncatalog.midia.dtos.MidiaBuscaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Mídias", description = "Operações globais de busca no catálogo")
@RequiredArgsConstructor
@RestController
@RequestMapping("/midias")
public class MidiaController {

    private final MidiaService service;


    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar mídias por título",
            description = """
                    Busca mídias pelo título (parcial e ignorando maiúsculas/minúsculas),
                    retornando o tipo da mídia e o artista ou diretor relacionado.
                    """
    )
    public ResponseEntity<List<MidiaBuscaDTO>> buscarPorTitulo(
            @RequestParam String titulo
    ) {

        List<MidiaBuscaDTO> response = service.buscarPorTitulo(titulo);

        return ResponseEntity.ok(response);
    }
}
